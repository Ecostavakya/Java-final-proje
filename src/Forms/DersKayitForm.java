package Forms;

import javax.swing.table.TableModel;
import Models.Ders;
import Services.FileService;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class DersKayitForm extends JFrame {

    private JTextField tfDersKodu;
    private JButton btnOk;
    private JTextField tfDersIsmi;
    private JPanel DersKayitForm;
    private JButton btnClear;
    private JComboBox cbDersFakulte;
    private JComboBox cbDersDepartman;
    private JTextField tfDersArama;
    private JTable tableDers;
    private JScrollPane scrollPane;

    DefaultTableModel tableModel;

    Ders ders = new Ders();
    String dersKodu = ders.DersKodu;
    String dersIsmi = ders.DersIsmi;
    String dersFakülte = ders.DersFakülte;
    String dersDepartman = ders.DersDepartman;
    public DersKayitForm() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dersKodu = tfDersKodu.getText();
                dersIsmi = tfDersIsmi.getText();
                System.out.println(dersKodu);


                FileService fileService = new FileService();
                fileService.writeToFile("src/Data", "Ders.txt", dersKodu, dersIsmi,dersFakülte,dersDepartman);


                updateTable();
            }
        });

        setContentPane(DersKayitForm);
        setTitle("Ders Kayıt Form");
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfDersKodu.setText("");
                tfDersIsmi.setText("");
            }
        });


        String[] columnNames = {"Ders Kodu", "Ders İsmi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableDers.setModel(tableModel);


        updateTable();
        tfDersArama.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String input = tfDersArama.getText().toLowerCase().trim();
                TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableDers.getModel());
                tableDers.setRowSorter(rowSorter);

                rowSorter.setRowFilter(RowFilter.regexFilter("(?i).*" + input + ".*"));
            }
        });

        cbDersFakulte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFaculty = (String) cbDersFakulte.getSelectedItem();
                if (selectedFaculty != null) {

                    List<String> departmentList = FileService.getDepartmentsFromFile("src/Data/" + selectedFaculty + ".txt");

                    if (departmentList != null) {
                        cbDersDepartman.setEnabled(true);
                        cbDersDepartman.removeAllItems();
                        for (String department : departmentList) {
                            cbDersDepartman.addItem(department);
                        }
                        cbDersDepartman.setSelectedIndex(-1);
                    }
                }
            }
        });
    }

    private void updateTable() {

        FileService fileService = new FileService();
        List<String> dersBilgileri = fileService.getDersBilgileri("src/Data/Ders.txt");


        tableModel.setRowCount(0);


        for (String dersBilgisi : dersBilgileri) {

            String[] parts = dersBilgisi.split(", ");
            String dersKodu = "";
            String dersIsmi = "";

            for (String part : parts) {
                if (part.startsWith("Ders Kodu:")) {
                    dersKodu = part.substring("Ders Kodu: ".length());
                } else if (part.startsWith("Ders İsmi:")) {
                    dersIsmi = part.substring("Ders İsmi: ".length());
                }
            }


            String[] rowData = {dersKodu, dersIsmi};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        DersKayitForm dersKayitForm = new DersKayitForm();
    }
}
