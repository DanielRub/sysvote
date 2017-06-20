package controller;

import controller.util.LoginSessionBean;
import entity.Admin;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String login, password;
    private Admin connectedAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getConnectedAdmin() {
        return connectedAdmin;
    }

    public void setConnectedAdmin(Admin connectedAdmin) {
        this.connectedAdmin = connectedAdmin;
    }

    @PersistenceContext(unitName = "com.mycompany_sysvote_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    //validate login
    public String validateUsernamePassword() {
        TypedQuery<Admin> namedQuery = getEm().createNamedQuery("Admin.findByLogin", Admin.class);
        namedQuery.setParameter("login", getLogin());
        Admin admin = new Admin();
        if (!namedQuery.getResultList().isEmpty()) {
            admin = namedQuery.getSingleResult();
            boolean valid = (admin.getPassword().equals(getPassword()));
            if (valid) {
                setConnectedAdmin(admin);
                HttpSession session = LoginSessionBean.getSession();
                session.setAttribute("login", getLogin());
                return "index";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "identifiants incorrects",
                                "veillez entrer des données correctes"));
                return "login";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "identifiants incorrects",
                            "veillez entrer des données correctes"));
            return "login";
        }

    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = LoginSessionBean.getSession();
        session.invalidate();
        return "/login";
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
