package controller;

import entity.Candidat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.StreamedContent;

@Named(value = "candidatController")
@SessionScoped
public class CandidatController extends AbstractController<Candidat> {

    public CandidatController() {
        // Inform the Abstract parent controller of the concrete Candidat Entity
        super(Candidat.class);
    }

    /**
     * Sets the "items" attribute with a collection of Electeur entities that
     * are retrieved from Candidat?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Electeur page
     */
    public String navigateElecteurCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Electeur_items", this.getSelected().getElecteurCollection());
        }
        return "/electeur/index";
    }
    private String filename;
    private StreamedContent streamedContentPic;
    private File[] tabfile;

    @PostConstruct
    private void init(){
        File file = new File("sysvote" + File.separator + "images" + File.separator + "photocam" + File.separator + "candidats");
        tabfile=file.listFiles();
    }
    
    public StreamedContent getStreamedContentPic(String id) throws FileNotFoundException {
        System.out.println("id = " + id);
        boolean isTrue=false;
        File aFile=null;
        for (File file : tabfile) {
            isTrue = file.getName().contains(id);
            aFile=file;
        }
        if(isTrue) {
            return streamedContentPic = new org.primefaces.model.DefaultStreamedContent(
                    new java.io.FileInputStream(aFile), "image/png");
        }
        return streamedContentPic;
        
    }

    public void setStreamedContentPic(StreamedContent streamedContentPic) {
        this.streamedContentPic = streamedContentPic;
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public void oncapture(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //  String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
        //                             File.separator + "images" + File.separator + "photocam" + File.separator + filename + ".png";

        String newFileName = "sysvote"
                + File.separator + "images" + File.separator + "photocam" + File.separator + "candidats" + File.separator + filename + ".png";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    //@PostConstruct
    public StreamedContent getGraphicImage(String id) throws FileNotFoundException {
        System.out.println("id = " + id);
        if (new File("sysvote" + File.separator + "images" + File.separator + "photocam" + File.separator + "candidats" + File.separator + id + ".png").exists()) {
            System.out.println("exist");
            return new org.primefaces.model.DefaultStreamedContent(
                    new java.io.FileInputStream("sysvote" + File.separator + "images" + File.separator + "photocam" + File.separator + "candidats" + File.separator + id + ".png"), "image/png");
        } else {
            System.out.println("does not exist");
            return null;
        }
    }

    @Override
    public void saveNewNoFeedBack(ActionEvent event) {
        super.getSelected().setPhotoName(filename);
        super.saveNewNoFeedBack(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.getSelected().setPhotoName(filename);
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }
}
