/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.javafxpi.gui;


import DataStorage.myDB;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextArea;
import com.pdfjet.A4;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.qoppa.pdfViewerFX.PDFViewer;
import edu.javafxpi.entities.Newsletter;
import edu.javafxpi.services.CRUDNewsletter;
import edu.javafxpi.gui.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

     

/**
 * FXML Controller class
 *
 * @author asus
 */
public class NewsController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private VBox content;
    @FXML
    private HBox menubar;
    @FXML
    private BorderPane border_pane1;
    @FXML
    private Label txtsujet;
    @FXML
    private Label txttitre;
    @FXML
    private Label txtdatecreation;
    @FXML
    private JFXTextArea txtcontent;
    int idp;
  private String selected;
  public static int id;
    /**
     * Initializes the controller class.
     */
    boolean flag = true;

    @FXML
    private void open_sidebar(ActionEvent event) throws IOException {
          BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            flag = false;
        } else {
            border_pane.setLeft(null);
            flag = true;
        }
    }
    public void initData() throws SQLException{
        // TODO
            CRUDNewsletter cn=new CRUDNewsletter();
           Newsletter list= cn.Newsletterbyid(id);
          txtsujet.setText(list.getSujet()); 
           txtcontent.setText(list.getContenu());
           txttitre.setText(list.getTitre());
           txtdatecreation.setText(list.getDatecreation().toString());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            try {
                    initData();
         Parent sidebar = FXMLLoader.load(getClass().getResource("/edu/javafxpi/gui/Sidebar.fxml"));
         border_pane.setLeft(sidebar);
           
         
         
     } catch (IOException ex) {
         Logger.getLogger(ListPostsController.class.getName()).log(Level.SEVERE, null, ex);
     }  catch (SQLException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void PartagerNew(ActionEvent event) throws DocumentException, FileNotFoundException, IOException, SQLException {
       String from = "gabsi.oumayma90@gmail.com"; String pass ="15/11/1995"; 
       
       List<String> too=new ArrayList<String>();
        try {
             String query = "select * from `projetpi`.`abonne`";
      
            
            Connection con = myDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
            
            int i =0;
       while(rs.next())
       {
      too.add(rs.getString("email"));
        
       }
            
        } catch (Exception e) {
            
        Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, e);
                }
       String[] to=new String[too.size()];
       for( int i = 0; i < too.size(); i++ ) 
       {
           to[i]=too.get(i);
       }
    CRUDNewsletter cn=new CRUDNewsletter();
           Newsletter list= cn.Newsletterbyid(id);
                
String subject = list.getTitre(); 
String body = "bonjour \n "+list.getSujet()+"\n "+list.getContenu();
        try {
           
            sendFromGMail(from, pass, to,subject  , body);
            new Alert(Alert.AlertType.INFORMATION,"Email bien recu" ).show();
        } catch (MessagingException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* Document docPdf = null;
		PdfWriter pdfWriter = null;
		File temp = File.createTempFile("monfichier",".pdf");
 
		pdfWriter = PdfWriter.getInstance(docPdf, new FileOutputStream(temp));
		docPdf.open();
 
		docPdf.add(new Paragraph("Mail de test"));
		docPdf.close();
		pdfWriter.close();
		String mailingList = "oussamasaidani@gmail.com";
		String subject = "Test";// Sujet du mail
		String content = "Mail pour tester l'envoi";
		String nomSource ="piecejointe";
 
		
		checkMail( temp, mailingList, subject, content, nomSource);
		temp.deleteOnExit();*/
    }
   /* public static void checkMail( File fichierTemp,
			String mailingList, String Subject, String content, String nomSource) {		
		String from = "gabsi.oumayma90@gmail.com";
 
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
                
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(session);
		try {
		msg.setFrom(new InternetAddress(from));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
				mailingList));
		msg.setSubject(Subject);
 
		                  Multipart mp = new MimeMultipart();
		                  MimeBodyPart mbp = new MimeBodyPart();
		mbp.setContent(content, "text/plain");
		mp.addBodyPart(mbp);
 
		                  FileDataSource source = new FileDataSource(fichierTemp);
		mbp.setDataHandler(new DataHandler(source));
		mbp.setFileName(nomSource);
		mp.addBodyPart(mbp);
 
		msg.setContent(mp);
 
		Transport.send(msg);
	} catch (Exception e) {
		System.err.println("L'envoi du mail a échoué : " + e.getMessage());
	}
 
	}*/
 private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) throws AddressException, MessagingException
    { 
        Properties props = System.getProperties(); 
    String host = "smtp.gmail.com";
    
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session); 

    message.setFrom(new InternetAddress(from));
InternetAddress[] toAddress = new InternetAddress[to.length];
 // To get the array of addresses// To get the array of addresses
for( int i = 0; i < to.length; i++ )  
    toAddress[i] = new InternetAddress(to[i]); 
    
for( int i = 0; i < toAddress.length; i++) { message.addRecipient(Message.RecipientType.TO, toAddress[i]); } 
message.setSubject(subject); message.setText(body); 
Transport transport = session.getTransport("smtp"); 
transport.connect(host, from, pass);
transport.sendMessage(message, message.getAllRecipients());
transport.close();

}
 
 
private FileChooser fc=new FileChooser() ;

    
    
     @FXML
    private void SavePDF(ActionEvent event) throws IOException, FileNotFoundException, SQLException, DocumentException {
        
        
              CRUDNewsletter outill = new CRUDNewsletter();
        this.genertePDFDoc(outill);
         
        
    }

    
     public void genertePDFDoc(CRUDNewsletter outil ) 
               throws FileNotFoundException, BadElementException, IOException, SQLException, DocumentException {
        Document document = new Document();

        FileChooser fileChooser = new FileChooser();
        document.addAuthor("*************** Newsletter ***************");
        document.addTitle("Project Report");
        document.addCreationDate();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf", "*.PDF");
        fileChooser.getExtensionFilters().add(extFilter);
        Window chooserWindow = null;

        //Show save file dialog
        File file = fileChooser.showSaveDialog(chooserWindow);
        
        String query = "select * from `projetpi`.`newsletter` where id="+ id;
        try {
            
            Connection con = myDB.getInstance().getConnexion();
            ResultSet rs = con.createStatement().executeQuery(query);
            
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph p1 = new Paragraph("\n\n\n\n");
            document.add(p1);
            
            Paragraph p2 = new Paragraph("\n\n\n\n Client" + "\n" + "---  Newsletter ---" );
            document.add(p2);
            
            Paragraph p3 = new Paragraph("\n\n");
            document.add(p3);
            
            
            
            PdfPTable my_report_table = new PdfPTable(4);
            //create a cell object
            PdfPCell table_cell;
            
               String sujet_outil =("sujet");
            table_cell=new PdfPCell(new Phrase(sujet_outil));
            my_report_table.addCell(table_cell);
            String titre_outil = ("titre");
            table_cell=new PdfPCell(new Phrase(titre_outil));
            my_report_table.addCell(table_cell);
            
            String contenu_outil = ("contenu");
            table_cell=new PdfPCell(new Phrase(contenu_outil));
            my_report_table.addCell(table_cell);
            
         
                
            String lienn_outil = ("lien");
            table_cell=new PdfPCell(new Phrase(lienn_outil));
            my_report_table.addCell(table_cell);
               
           
          
            
            
while( rs.next() ){
               
                  String sujet1_outil=rs.getString("sujet");
                table_cell=new PdfPCell(new Phrase(sujet1_outil));
                my_report_table.addCell(table_cell);
    
                String titre1_outil = rs.getString("titre");
                table_cell=new PdfPCell(new Phrase(titre1_outil));
                my_report_table.addCell(table_cell);
                
               
                String contenu1_outil = rs.getString("contenu");
                table_cell=new PdfPCell(new Phrase(contenu1_outil));
                my_report_table.addCell(table_cell);
                
                
                
            
                         
                  String lien_outil=rs.getString("lienDeAbonnement");
                table_cell=new PdfPCell(new Phrase(lien_outil));
                my_report_table.addCell(table_cell);
                         
                                    
            }
/* Attach report table to PDF */
                    
                    document.add(my_report_table);                       
                    document.close();
           
            
        } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }   catch (SQLException ex) {
            Logger.getLogger(Newsletter.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
    
    }  
}


  

    
    

