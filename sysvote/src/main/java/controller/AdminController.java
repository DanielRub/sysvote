package controller;

import entity.Admin;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "adminController")
@ViewScoped
public class AdminController extends AbstractController<Admin> {

    public AdminController() {
        // Inform the Abstract parent controller of the concrete Admin Entity
        super(Admin.class);
    }

}
