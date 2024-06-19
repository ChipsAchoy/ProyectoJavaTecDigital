/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.proyectomatricula.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author INTEL
 */
public class PlanEstudios {
    
    private EscuelaArea escArea;
    private int code;
    private String dateValid;
    private List<Curso> cursos;
    private List<String> bloques;
    private Map<String, List<Curso>> bloqueCursos;

    // Constructor

    /**
     *
     * @param escArea
     * @param code
     * @param dateValid
     */
    public PlanEstudios(EscuelaArea escArea, int code, String dateValid) {
        this.escArea = escArea;
        this.code = code;
        this.dateValid = dateValid;
        this.cursos = new ArrayList<>();
        this.bloques = new ArrayList<>();
        this.bloqueCursos = new HashMap<>();
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
    public String getDateValid() {
        return dateValid;
    }

    /**
     *
     * @param dateValid
     */
    public void setDateValid(String dateValid) {
        this.dateValid = dateValid;
    }

    /**
     *
     * @return
     */
    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     *
     * @param curso
     */
    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }

    /**
     *
     * @return
     */
    public List<String> getBloques() {
        return bloques;
    }

    /**
     *
     * @param bloque
     */
    public void addBloque(String bloque) {
        this.bloques.add(bloque);
        this.bloqueCursos.put(bloque, new ArrayList<>());
    }

    /**
     *
     * @return
     */
    public Map<String, List<Curso>> getBloqueCursos() {
        return bloqueCursos;
    }
    

    // Asociar un curso a un bloque

    /**
     *
     * @param curso
     * @param bloque
     */
    public void asociarCursoABloque(Curso curso, String bloque) {
        if (!this.bloques.contains(bloque)) {
            throw new IllegalArgumentException("El bloque no existe.");
        }
        if (!this.cursos.contains(curso)) {
            throw new IllegalArgumentException("El curso no existe en la lista de cursos.");
        }
        this.bloqueCursos.get(bloque).add(curso);
        
    }
}