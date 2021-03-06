package persistentie;

import com.jcraft.jsch.*;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

public class FileTransfer implements Runnable{

    private String server;
    private int port;
    private String login;
    private String password;

    private JSch jsch = null;
    private Session session = null;
    private Channel channel = null;
    private ChannelSftp c = null;

    private Job jobs = null;

    public FileTransfer() {
        server = "188.166.36.83";
        port = 22;
        login = "bob";
        password = "Pazaak2.0";
    }

    public FileTransfer(Job jobs) {
        server = "188.166.36.83";
        port = 22;
        login = "bob";
        password = "Pazaak2.0";

        this.jobs = jobs;
    }

    public void connect() {
        try {
            System.out.println("Initializing jsch");
            jsch = new JSch();
            session = jsch.getSession(login, server, port);

            session.setPassword(password.getBytes(Charset.forName("ISO-8859-1")));

            System.out.println("Jsch set StrictHostKeyChecking = no");
            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            System.out.printf("Connecting to " + server + ":" + port);
            session.connect();
            System.out.println("Connected!");

            System.out.println("Opening a channel ...");
            channel = session.openChannel("sftp");
            channel.connect();
            c = (ChannelSftp) channel;
            System.out.println("Channel sftp opened");

        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uploads a file to the sftp server
     * @param sourceFile      String path to sourceFile
     * @param destinationFile String path on the remote server
     * @throws IllegalArgumentException if connection and channel are not available or if an error occurs during upload.
     */
    public void uploadFile(String sourceFile, String destinationFile) throws IllegalArgumentException {
        if (c == null || session == null || !session.isConnected() || !c.isConnected()) {
            throw new IllegalArgumentException("Connection to server is closed. Open it first.");
        }

        try {
            System.out.println("Uploading file to server");
            c.put(sourceFile, "uploads/" + destinationFile);
            System.out.println("Upload successfull.");
        } catch (SftpException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    /**
     * Retrieves a file from the sftp server
     * @param destinationFile String path to the remote file on the server
     * @param sourceFile String path on the local fileSystem
     * @throws IllegalArgumentException if connection and channel are not available or if an error occurs during download.
     */
    public File retrieveFile(String sourceFile, String destinationFile) throws IllegalArgumentException {
        if (c == null || session == null || !session.isConnected() || !c.isConnected()) {
            throw new IllegalArgumentException("Connection to server is closed. Open it first.");
        }
        File file;

        try {
            System.out.println("Downloading file to server");
            c.get("uploads/" + destinationFile, sourceFile);
            System.out.println("Download successfull.");
            file = new File(sourceFile);
        } catch (SftpException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return file;
    }

    public void deleteFile(String destinationFile) throws IllegalArgumentException{
        if (c == null || session == null || !session.isConnected() || !c.isConnected()) {
            throw new IllegalArgumentException("Connection to server is closed. Open it first.");
        }

        try {
            System.out.println("Delete file server");
            c.rm("uploads/" + destinationFile);
            System.out.println("Delete successfull.");
        }catch(SftpException e){
            throw new IllegalArgumentException(e.getMessage());

        }
    }

    public void disconnect() {
        if (c != null) {
            System.out.println("Disconnecting sftp channel");
            c.disconnect();
        }
        if (channel != null) {
            System.out.println("Disconnecting channel");
            channel.disconnect();
        }
        if (session != null) {
            System.out.println("Disconnecting session");
            session.disconnect();
        }
    }


    @Override
    public void run() {
        connect();

        List<String> job;

        while (true){
            job = jobs.haalJob();

            switch (job.get(0)){
                case "DELETE":
                    deleteFile(job.get(1));
                    break;
                case "UPLOAD":
                    uploadFile(job.get(1), job.get(2));
                    break;
            }
        }
    }
}
