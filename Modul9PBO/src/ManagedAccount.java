import java.io.*;
import java.util.Scanner;

public class ManagedAccount {
    private String email, password, id;
    private int pilihan;
    private final String CLEAR_CONSOLE = "\u001b[2J\u001b[H";
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private Scanner scanner = new Scanner(System.in);

    public void menu() throws IOException {
        System.out.println("----------------------------------");
        System.out.println("1. Masukan Data akun");
        System.out.println("2. Lihat Data akun");
        System.out.println("3. Keluar Program");
        System.out.println("-----------------------------------");
        System.out.print("Option : ");

        pilihan = Integer.parseInt(input.readLine());
        if (pilihan == 1) {
            inputData();
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

    public void inputData() throws IOException {
        System.out.print("ID (unik) : ");
        id = input.readLine();
        System.out.print("Email     : ");
        email = input.readLine();
        System.out.print("Password  : ");
        password = input.readLine();
    }

    public void simpan() throws FileNotFoundException {
        String namaFile = "Rahasia.txt";

        FileOutputStream outFile = new FileOutputStream(namaFile, true);
        try {
            DataOutputStream outStream = new DataOutputStream(outFile);
            outStream.writeUTF(id);
            outStream.writeUTF(email);
            outStream.writeUTF(password);
            outStream.close();
        } catch (IOException e) {
            System.out.println("IOERROR : " + e.getMessage() + "\n");
        }
    }

    public void baca() {
        String namaFile = "Rahasia.txt";
        try {
            FileInputStream inFile = new FileInputStream(namaFile);
            DataInputStream inStream = new DataInputStream(inFile);
            while (inStream.available() > 0) {
                id = inStream.readUTF();
                email = inStream.readUTF();
                password = inStream.readUTF();

                System.out.println("ID       : " + id + "\nEmail    : " + email + "\nPassword : " + password);
                System.out.println("-----------------------");
                System.out.println();
            }
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + namaFile + "Tidak ada!\n");
        } catch (IOException ex) {
            System.out.println("IOERROR : " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        ManagedAccount accountManager = new ManagedAccount();
        try {
            accountManager.menu();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan pada input/output.");
        }
    }
}
