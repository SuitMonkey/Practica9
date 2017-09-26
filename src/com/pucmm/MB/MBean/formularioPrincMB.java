package com.pucmm.MB.MBean;

import com.pucmm.MB.Modelo.Contacto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Francis Cáceres on 25/9/2017.
 */
//@Named("miFormulario")
@ManagedBean(name = "miFormulario")
@SessionScoped
public class formularioPrincMB implements Serializable{
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo;

    private Set<Contacto> contact;

    @PostConstruct
    private void inicializando(){
        contact = new HashSet<>();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().putIfAbsent("id",1);
    }

    @PreDestroy
    private void destruyendo(){
        System.out.println("Destruyendo el Beans de formulario....");
    }


    public void agregarContacto() {
        contact = new HashSet<>();

        String id = "Cnt " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        Contacto cont = new Contacto(this.nombre,this.apellido);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(id, cont);

        int mId = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id") + 1;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", mId);

        Map<String,Object> sesionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        for(Map.Entry<String,Object> entry : sesionMap.entrySet()){
            if(entry.getKey().contains("Cnt")){
                System.out.println(entry.getKey() + " : " + entry.getValue());
                contact.add((Contacto)entry.getValue());
            }
        }
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Contacto> getContact() {
        return contact;
    }

    public void setContact(Set<Contacto> contact) {
        this.contact = contact;
    }
}
