package Services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public List<String> getDersIsimleri(String path) {
        List<String> dersIsimleri = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                for (String part : parts) {
                    if (part.startsWith("Ders İsmi:")) {
                        dersIsimleri.add(part.substring("Ders İsmi: ".length()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dersIsimleri;
    }

    public List<String> getOgrenciDersleri(String path) {
        List<String> ogrenciDersleri = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                for (String part : parts) {
                    if (part.startsWith("Ogrenci Ders:")) {
                        ogrenciDersleri.add(part.substring("Ogrenci Ders: ".length()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ogrenciDersleri;
    }
    public static List<String> getDepartmentsFromFile(String filePath) {
        List<String> departmentList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                departmentList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return departmentList;
    }
    public List<String> getDersBilgileri(String path) {
        List<String> dersBilgileri = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dersBilgileri.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dersBilgileri;
    }
    public List<String> getOgretmenDers(String path) {
        List<String> dersBilgileri = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dersBilgileri.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dersBilgileri;
    }


    public void writeToFile(String path, String fileName, String data1, String data2, String data3, String data4) {
        createFile(path, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + fileName, true))) {
            writer.write("Ders Kodu: " + data1 + ", Ders İsmi: " + data2 + ", Fakülte:" + data3 + ", Departman:" + data4);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile2(String path, String fileName, String data1, String data2, String data3, String data4) {
        createFile(path, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + fileName, true))) {
            writer.write("Ogrenci Adı: " + data1 + ", Ogrenci Soyadı: " + data2 + ", Ogrenci No: " + data3 + ", Ogrenci Ders: " + data4);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeToFile3(String path, String fileName, String data1, String data2, String data3, String data4) {
        createFile(path, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + fileName, true))) {
            writer.write("Ögretmen Adı: " + data1 + ", Ögretmen Soyadı: " + data2 + ", Ogretmen No: " + data3 + ", Ogretmen Ders: " + data4);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String path, String fileName) {
        File directory = new File(path);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Klasör oluşturuldu: " + directory.getAbsolutePath());
            } else {
                System.out.println("Klasör oluşturma başarısız!");
            }
        } else {
            System.out.println("Klasör zaten mevcut: " + directory.getAbsolutePath());
        }

        File file = new File(path, fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("Metin dosyası oluşturuldu: " + file.getName());
            } else {
                System.out.println("Metin dosyası zaten mevcut.");
            }
        } catch (IOException e) {
            System.out.println("Dosya oluşturma hatası: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FileService fileService = new FileService();
        String filePath = "src/Data/Ders.txt";
        List<String> dersIsimleri = fileService.getDersIsimleri(filePath);

        System.out.println("Ders Isimleri:");
        for (String dersIsmi : dersIsimleri) {
            System.out.println(dersIsmi);
        }


        String ogrenciFilePath = "src/Data/Ogrenci.txt";
        List<String> ogrenciDersleri = fileService.getOgrenciDersleri(ogrenciFilePath);
        System.out.println("\nOgrenci Dersleri:");
        for (String ogrenciDers : ogrenciDersleri) {
            System.out.println(ogrenciDers);
        }


        String ogretmenFilePath = "src/Data/Ogretmen.txt";
        List<String> ogretmenDersleri = fileService.getOgretmenDers(ogretmenFilePath);
        System.out.println("\nOgretmen Dersleri:");
        for (String ogretmenDers : ogretmenDersleri) {
            System.out.println(ogretmenDers);
        }
    }

}
