package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import Servicio.Hospital;
import modelo.Paciente;

public class MainGUI extends JFrame {

    private final Hospital hospital = new Hospital();
    private final JTextArea areaSalida = new JTextArea();

    public MainGUI() {
        setTitle("Sistema de Emergencias Hospitalarias");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior: titulo
        JLabel titulo = new JLabel("SISTEMA DE EMERGENCIAS HOSPITALARIAS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(new EmptyBorder(12, 0, 12, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel central con pestañas
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Registrar", panelRegistrar());
        tabs.addTab("Buscar", panelBuscar());
        tabs.addTab("Cola de atencion", panelCola());
        tabs.addTab("Altas", panelAltas());
        tabs.addTab("Listado / Reporte", panelListadoReporte());
        add(tabs, BorderLayout.CENTER);

        // Panel inferior: salida
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setPreferredSize(new Dimension(900, 220));
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(scroll, BorderLayout.SOUTH);
    }

    private void mostrar(String texto) {
        areaSalida.setText(texto);
        areaSalida.setCaretPosition(0);
    }

    // ---------- Pestaña Registrar ----------
    private JPanel panelRegistrar() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;

        JTextField txtDni = new JTextField(15);
        JTextField txtNombres = new JTextField(15);
        JTextField txtEdad = new JTextField(15);
        JComboBox<String> cbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        JComboBox<String> cbGravedad = new JComboBox<>(new String[]{"Leve", "Moderado", "Grave"});
        JTextField txtFecha = new JTextField(15);

        String[] etiquetas = {"DNI (numerico):", "Nombres:", "Edad:", "Sexo:", "Gravedad:", "Fecha ingreso:"};
        Component[] campos = {txtDni, txtNombres, txtEdad, cbSexo, cbGravedad, txtFecha};

        for (int i = 0; i < etiquetas.length; i++) {
            g.gridx = 0; g.gridy = i; g.weightx = 0;
            p.add(new JLabel(etiquetas[i]), g);
            g.gridx = 1; g.weightx = 1;
            p.add(campos[i], g);
        }

        JButton btnRegistrar = new JButton("Registrar paciente");
        JButton btnGenerar = new JButton("Generar 1000 de prueba");
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(btnRegistrar);
        botones.add(btnGenerar);
        g.gridx = 0; g.gridy = etiquetas.length; g.gridwidth = 2;
        p.add(botones, g);

        btnRegistrar.addActionListener(e -> {
            String dni = txtDni.getText().trim();
            String nombres = txtNombres.getText().trim();
            String edadStr = txtEdad.getText().trim();
            if (dni.isEmpty() || nombres.isEmpty() || edadStr.isEmpty()) {
                mostrar("Complete DNI, nombres y edad.");
                return;
            }
            if (!dni.matches("\\d+")) {
                mostrar("El DNI debe ser numerico (lo exige el arbol ABB).");
                return;
            }
            int edad;
            try {
                edad = Integer.parseInt(edadStr);
            } catch (NumberFormatException ex) {
                mostrar("La edad debe ser un numero entero.");
                return;
            }
            String msg = hospital.registrarPaciente(dni, nombres, edad,
                    (String) cbSexo.getSelectedItem(),
                    (String) cbGravedad.getSelectedItem(),
                    txtFecha.getText().trim());
            mostrar(msg);
            if (msg.startsWith("Paciente registrado")) {
                txtDni.setText(""); txtNombres.setText(""); txtEdad.setText(""); txtFecha.setText("");
            }
        });

        btnGenerar.addActionListener(e -> mostrar(hospital.generarPacientesPrueba()));
        return p;
    }

    // ---------- Pestaña Buscar / Historial ----------
    private JPanel panelBuscar() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));

        p.add(new JLabel("DNI:"));
        JTextField txtDni = new JTextField(15);
        p.add(txtDni);

        JButton btnBuscar = new JButton("Buscar paciente");
        JButton btnHistorial = new JButton("Ver historial");
        JButton btnEnviarCola = new JButton("Enviar a cola");
        JButton btnAlta = new JButton("Dar de alta");
        p.add(btnBuscar); p.add(btnHistorial); p.add(btnEnviarCola); p.add(btnAlta);

        btnBuscar.addActionListener(e -> {
            String dni = txtDni.getText().trim();
            if (!validarDni(dni)) return;
            Paciente pac = hospital.buscarPaciente(dni);
            mostrar(pac != null ? pac.toString() : "Paciente no encontrado.");
        });
        btnHistorial.addActionListener(e -> {
            String dni = txtDni.getText().trim();
            if (!validarDni(dni)) return;
            mostrar(hospital.mostrarHistorial(dni));
        });
        btnEnviarCola.addActionListener(e -> {
            String dni = txtDni.getText().trim();
            if (!validarDni(dni)) return;
            mostrar(hospital.enviarCola(dni));
        });
        btnAlta.addActionListener(e -> {
            String dni = txtDni.getText().trim();
            if (!validarDni(dni)) return;
            mostrar(hospital.darAlta(dni));
        });
        return p;
    }

    private boolean validarDni(String dni) {
        if (dni.isEmpty() || !dni.matches("\\d+")) {
            mostrar("Ingrese un DNI numerico valido.");
            return false;
        }
        return true;
    }

    // ---------- Pestaña Cola ----------
    private JPanel panelCola() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        JButton btnMostrar = new JButton("Mostrar cola");
        JButton btnAtender = new JButton("Atender siguiente");
        p.add(btnMostrar); p.add(btnAtender);
        btnMostrar.addActionListener(e -> mostrar(hospital.mostrarCola()));
        btnAtender.addActionListener(e -> mostrar(hospital.atenderPaciente()));
        return p;
    }

    // ---------- Pestaña Altas ----------
    private JPanel panelAltas() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        JButton btnOrden = new JButton("Altas (inicio -> fin)");
        JButton btnInverso = new JButton("Altas (fin -> inicio)");
        p.add(btnOrden); p.add(btnInverso);
        btnOrden.addActionListener(e -> mostrar(hospital.mostrarAltas()));
        btnInverso.addActionListener(e -> mostrar(hospital.mostrarAltasInverso()));
        return p;
    }

    // ---------- Pestaña Listado / Reporte ----------
    private JPanel panelListadoReporte() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        JButton btnListar = new JButton("Mostrar todos los pacientes");
        JButton btnReporte = new JButton("Mostrar reporte estadistico");
        p.add(btnListar); p.add(btnReporte);
        btnListar.addActionListener(e -> mostrar(hospital.mostrarPacientes()));
        btnReporte.addActionListener(e -> mostrar(hospital.mostrarReporte()));
        return p;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
