import java.io.*;
import java.io.IOException;
import java.util.Scanner;

abstract class Manusia {
    protected String nama, alamat, jenisKelamin;

    public abstract void menu() throws IOException;

    protected void InputData () throws IOException {
        System.out.println("Nama : ");
        System.out.println("Alamat : ");
        System.out.println("Jenis Kelamin (L/P) : ");
    }
}

interface simpanData {
    void simpan() throws FileNotFoundException;
}

interface bacaData {
    void baca();
}

public class Mahasiswa extends Manusia implements simpanData, bacaData {
    private String nim, programStudi, jurusan;
    private int pilihan;
    final String CLEAR_CONSOLE = "\u001b[2J\u001b[H";

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner = new Scanner(System.in);

    @Override
    public void menu() throws IOException {
        System.out.println("==================================");
        System.out.println("MENU PILIHAN");
        System.out.println("----------------------------------");
        System.out.println("1. Input Data Mahasiswa");
        System.out.println("2. Lihat Data Mahasiswa");
        System.out.println("3. Keluar Program");
        System.out.println("-----------------------------------");
        System.out.print("Pilihan Anda : ");
       
        pilihan = Integer.parseInt(input.readLine());
        if (pilihan == 1) {
            InputData();
            simpan();
            scanner.nextLine();
        } else if (pilihan == 2) {
            baca();
            scanner.nextLine();
        } else {
            System.exit(0);
        }
        System.out.println(CLEAR_CONSOLE);
        menu();
    }

    @Override
    public void InputData() throws IOException{
        System.out.print("Nama                : ");
        nama = input.readLine();
        System.out.print("Alamat              : ");
        alamat = input.readLine();
        System.out.print("Jenis Kelamin (L/P) : ");
        jenisKelamin = input.readLine();
        System.out.print("NIM                 : ");
        nim = input.readLine();
        System.out.print("Program Studi       : ");
        programStudi = input.readLine();
        System.out.print("Jurusan             : ");
        jurusan = input.readLine();
    }

    @Override
    public void simpan() throws FileNotFoundException {
        String namaFile = "Mahasiswa.txt";

        FileOutputStream outFile = new FileOutputStream(namaFile, true);
        try {
            DataOutputStream outStream = new DataOutputStream(outFile);
            outStream.writeUTF(nama);
            outStream.writeUTF(alamat);
            outStream.writeUTF(jenisKelamin);
            outStream.writeUTF(nim);
            outStream.writeUTF(programStudi);
            outStream.writeUTF(jurusan);
            outStream.close();
        }
        catch(IOException e){
            System.out.println("IOERROR : " + e.getMessage() + "\n");
        }
    }

    @Override
    public void baca() {
        String namaFile = "Mahasiswa.txt";
        String namaMhs, alamatMhs, jenisKelaminMhs, nimMhs, fakultasMhs, jurusanMhs;
        try{
            FileInputStream inFile = new FileInputStream(namaFile);
            DataInputStream inStream = new DataInputStream(inFile);
            while (inStream.available() > 0) {
                namaMhs = inStream.readUTF();
                alamatMhs = inStream.readUTF();
                jenisKelaminMhs = inStream.readUTF();
                nimMhs = inStream.readUTF();
                fakultasMhs = inStream.readUTF();
                jurusanMhs = inStream.readUTF();

                System.out.println("Nama                : " + namaMhs + "\nAlamat              : " + alamatMhs + "\nJenis Kelamin       : " + jenisKelaminMhs);
                System.out.println("NIM                 : " + nimMhs + "\nProgram Studi       : " + fakultasMhs + "\nJurusan             : " + jurusanMhs);
                System.out.println("-----------------------");
                System.out.println();
            }
            inStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File " + namaFile + "Tidak ada!\n");
        }
        catch(IOException ex){
            System.out.println("IOERROR : " + ex.getMessage() + "\n");
        }
    }
}

final class classUtama {
    public static void main(String[] args) throws IOException {
        Mahasiswa appMahasiswa = new Mahasiswa();

        appMahasiswa.menu();
    }
}
