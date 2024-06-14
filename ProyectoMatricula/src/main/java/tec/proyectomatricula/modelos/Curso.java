/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.proyectomatricula.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INTEL
 */
public class Curso {
    
    private EscuelaArea escArea;
    private String name;
    private int code;
    private int creditos;
    private int horasLectivas;
    private List<Curso> requisitos;
    private List<Curso> correquisitos;

    // Constructor
    public Curso(EscuelaArea escArea, String name, int code, int creditos, int horasLectivas) {
        this.escArea = escArea;
        this.name = name;
        this.code = code;
        this.creditos = creditos;
        this.horasLectivas = horasLectivas;
        this.requisitos = new ArrayList<>();
        this.correquisitos = new ArrayList<>();
    }

    // Getters and Setters
    public EscuelaArea getEscArea() {
        return escArea;
    }

    public void setEscArea(EscuelaArea escArea) {
        this.escArea = escArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHorasLectivas() {
        return horasLectivas;
    }

    public void setHorasLectivas(int horasLectivas) {
        this.horasLectivas = horasLectivas;
    }

    public List<Curso> getRequisitos() {
        return requisitos;
    }

    public void addRequisito(Curso requisito) {
        this.requisitos.add(requisito);
    }

    public List<Curso> getCorrequisitos() {
        return correquisitos;
    }

    public void addCorrequisito(Curso correquisito) {
        this.correquisitos.add(correquisito);
    }
}