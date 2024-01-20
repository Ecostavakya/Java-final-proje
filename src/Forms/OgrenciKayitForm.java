package Forms;

import Models.Ogrenci;
import Services.FileService;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OgrenciKayitForm extends JFrame {

    private JTextField tfOgrenciAdi;
    private JTextField tfOgrenciSoyadi;
    private JButton btnOK;
    private JTextField tfOgrenciNo;
    private JPanel OgrenciKayitForm;
    private JButton btnClear;
    private JComboBox cbOgrenciDers;
    private JButton btnGoster;
    Ogrenci ogrenci = new Ogrenci();
    String ogrenciAdi = ogrenci.OgrenciAdi;
    String ogrenciNo = ogrenci.OgrenciNo;
    String ogrenciSoyadi = ogrenci.OgrenciSoyadi;
    String ogrenciDers = ogrenci.OgrenciDers;
    public OgrenciKayitForm() {

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ogrenciAdi = tfOgrenciAdi.getText();
                ogrenciDers = (String) cbOgrenciDers.getSelectedItem();
                ogrenciSoyadi = tfOgrenciSoyadi.getText();
                ogrenciNo = tfOgrenciNo.getText();
                System.out.println("Öğrenci Adı: " + ogrenciAdi + ", Soyadı: " + ogrenciSoyadi + ", Öğrenci No: " + ogrenciNo);
                FileService fileService = new FileService();
                fileService.writeToFile2("src/Data","Ogrenci.txt",ogrenciAdi,ogrenciSoyadi,ogrenciNo,ogrenciDers);

            }
        });

        btnGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileService fileService = new FileService();
                List <String> dersList = fileService.getDersIsimleri("src/Data/Ders.txt");

                if (dersList != null) {
                    cbOgrenciDers.removeAllItems();
                    for (String Ders : dersList) {
                        cbOgrenciDers.addItem(Ders);
                    }
                    cbOgrenciDers.setSelectedIndex(-1);
                }
            }
        });



        setContentPane(OgrenciKayitForm);
        setTitle("Öğrenci Kayıt Formu");
        setMinimumSize(new Dimension(600, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfOgrenciAdi.setText("");
                tfOgrenciNo.setText("");
                tfOgrenciSoyadi.setText("");
                cbOgrenciDers.setSelectedIndex(-1);
            }
        });
    }

    public static void main(String[] args) {
        OgrenciKayitForm ogrenciKayitForm = new OgrenciKayitForm();
    }
}