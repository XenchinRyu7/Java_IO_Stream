import java.io.*;

class BacaFile {
    public static void main(String[] args) throws IOException {
        String namaFIle = "PBO.txt";
        String namaMhs, jurusan, angkatan;
        try{
            FileInputStream inFile = new FileInputStream(namaFIle);
            DataInputStream inStream = new DataInputStream(inFile);
            namaMhs = inStream.readUTF();
            jurusan = inStream.readUTF();
            angkatan = inStream.readUTF();
            inStream.close();
            System.out.println("Nama : " + namaMhs + "\nJurusan : " + jurusan + "\nAngkatan  : " + angkatan);
        }
        catch(FileNotFoundException e){
            System.out.println("File " + namaFIle + "Tidak ada!\n");
        }
        catch(IOException ex){
            System.out.println("IOERROR : " + ex.getMessage() + "\n");
        }
    }
}
