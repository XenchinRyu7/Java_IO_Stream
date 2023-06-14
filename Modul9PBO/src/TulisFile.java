import java.io.*;

class TulisFile {
    public static void main(String[] args) throws IOException {
        String namaFIle = "PBO.txt"; // nama file yang dibuat
        // isi file yang ditulis
        String namaMhs = "Rio Andriyat Krisdiawan, M.Kom\n";
        String jurusan = "Teknik Informatika\n";
        String angkatan = "2007";
        FileOutputStream outFile = new FileOutputStream(namaFIle);
        try {
            DataOutputStream outStream = new DataOutputStream(outFile);
            outStream.writeUTF(namaMhs);
            outStream.writeUTF(jurusan);
            outStream.writeUTF(angkatan);
            outStream.close();
        }
        catch(IOException e){
            System.out.println("IOERROR : " + e.getMessage() + "\n");
        }
    }
}
