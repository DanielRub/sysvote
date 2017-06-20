package controller;

import entity.Electeur;
import javax.inject.Named;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.StreamedContent;

@Named(value = "electeurController")
@SessionScoped
public class ElecteurController extends AbstractController<Electeur> {

    @Inject
    private CandidatController idCandidatVoteController;
    String digitprint = "";
    private StreamedContent streamedContent;

    public StreamedContent getStreamedContent(String st) throws FileNotFoundException {
        System.out.println("getting graphicImage id = " + st);
        return streamedContent = getGraphicImage(st);
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    @PostConstruct
    private void init() {
        System.out.println("initializing..");
        setStreamedContent(null);
    }

    public void reset(ActionEvent action) {
        System.out.println("resetting...");
    }

    public ElecteurController() {
        // Inform the Abstract parent controller of the concrete Electeur Entity
        super(Electeur.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idCandidatVoteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Candidat controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCandidatVote(ActionEvent event) throws InstantiationException, IllegalAccessException {
        if (this.getSelected() != null && idCandidatVoteController.getSelected() == null) {
            idCandidatVoteController.setSelected(this.getSelected().getIdCandidatVote());
        }
    }
    private String filename;

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);

        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public void oncapture(CaptureEvent captureEvent) throws FileNotFoundException {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = "sysvote"
                + File.separator + "images" + File.separator + "photocam" + File.separator + "electeurs" + File.separator + filename + ".png";
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
        setStreamedContent(getStreamedContent(filename));
        System.out.println("the new file is " + filename);
    }

    public StreamedContent getGraphicImage(String id) throws FileNotFoundException {
        if (new File("sysvote" + File.separator + "images" + File.separator + "photocam" + File.separator + "electeurs" + File.separator + id + ".png").exists()) {
            return new org.primefaces.model.DefaultStreamedContent(
                    new java.io.FileInputStream("sysvote" + File.separator + "images" + File.separator + "photocam" + File.separator + "electeurs" + File.separator + id + ".png"), "image/png");
        } else {
            return null;
        }
    }

    @Override
    public void saveNewNoFeedBack(ActionEvent event) {
        super.getSelected().setPhotoName(filename);
        super.getSelected().setEmpreinte(digitprint);
        super.saveNewNoFeedBack(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.getSelected().setPhotoName(filename);
        super.getSelected().setEmpreinte(digitprint);
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }

    public void captureEmpreinte(ActionEvent event) {
        System.out.println("capturing digit print...");
        digitprint = "sdfecxw43";
    }

    public String getDigitprint() {
        return digitprint;
    }

    public void setDigitprint(String digitprint) {
        this.digitprint = digitprint;
    }

}
