package Forms;

import Services.FileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OgretimGorevlisiForm extends JFrame {
    private JTextField tfOgretmenNo;
    private JTextField tfOgretmenAd;
    private JPanel OgretimGorevlisiForm;
    private JTextField tfOgretmenSoyad;
    private JButton clearButton;
    private JButton okButton;
    private JComboBox<String> cbOgretmenDers;
    private JButton btnDersGoster;

    public OgretimGorevlisiForm() {
        // Initialize components, set layout, etc.

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfOgretmenNo.setText("");
                tfOgretmenAd.setText("");
                tfOgretmenSoyad.setText("");
                cbOgretmenDers.setSelectedIndex(-1);
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ogretmenAd = tfOgretmenAd.getText();
                String ogretmenSoyad = tfOgretmenSoyad.getText();
                String ogretmenNo = tfOgretmenNo.getText();
                String ogretmenDers = (String) cbOgretmenDers.getSelectedItem();

                System.out.println("Öğretmen AD: " + ogretmenAd + ", Öğretmen Soyad: " + ogretmenSoyad + ", Öğretmen No: " + ogretmenNo);

                // Dosyaya yazma işlemi
                FileService fileService = new FileService();
                fileService.writeToFile3("src/Data", "Ogretmen.txt", ogretmenAd, ogretmenSoyad, ogretmenNo, ogretmenDers);
            }
        });
        cbOgretmenDers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileService fileService = new FileService();
                List<String> dersList;
                dersList = fileService.getDersIsimleri("src/Data/Ders.txt");
                if (dersList != null) {
                    cbOgretmenDers.removeAllItems();
                    for (String Ders : dersList) {
                        cbOgretmenDers.addItem(Ders);
                    }
                    cbOgretmenDers.setSelectedIndex(-1);
                }
            }
        });
        setContentPane(OgretimGorevlisiForm);
        setTitle("Ogretim Görevlisi Form");
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnDersGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileService fileService = new FileService();
                List<String> dersList;
                dersList = fileService.getDersIsimleri("src/Data/Ders.txt");
                if (dersList != null) {
                    cbOgretmenDers.removeAllItems();
                    for (String Ders : dersList) {
                        cbOgretmenDers.addItem(Ders);
                    }
                    cbOgretmenDers.setSelectedIndex(-1);
                }
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OgretimGorevlisiForm ogretimGorevlisiForm = new OgretimGorevlisiForm();
            ogretimGorevlisiForm.setTitle("Öğretim Görevlisi Kayıt Formu");
            ogretimGorevlisiForm.setSize(400, 300);
            ogretimGorevlisiForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ogretimGorevlisiForm.setVisible(true);
        });
    }
}

