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

    /**
     *
     * @param escArea
     * @param name
     * @param code
     * @param creditos
     * @param horasLectivas
     */
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

    /**
     *
     * @return
     */
    public EscuelaArea getEscArea() {
        return escArea;
    }

    /**
     *
     * @param escArea
     */
    public void setEscArea(EscuelaArea escArea) {
        this.escArea = escArea;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return
     */
    public int getCreditos() {
        return creditos;
    }

    /**
     *
     * @param creditos
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    /**
     *
     * @return
     */
    public int getHorasLectivas() {
        return horasLectivas;
    }

    /**
     *
     * @param horasLectivas
     */
    public void setHorasLectivas(int horasLectivas) {
        this.horasLectivas = horasLectivas;
    }

    /**
     *
     * @return
     */
    public List<Curso> getRequisitos() {
        return requisitos;
    }

    /**
     *
     * @param requisito
     */
    public void addRequisito(Curso requisito) {
        this.requisitos.add(requisito);
    }

    /**
     *
     * @return
     */
    public List<Curso> getCorrequisitos() {
        return correquisitos;
    }

    /**
     *
     * @param correquisito
     */
    public void addCorrequisito(Curso correquisito) {
        this.correquisitos.add(correquisito);
    }
}