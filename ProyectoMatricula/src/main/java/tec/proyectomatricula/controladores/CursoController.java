/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.proyectomatricula.controladores;

import java.util.ArrayList;
import java.util.List;
import tec.proyectomatricula.modelos.Curso;

/**
 *
 * @author INTEL
 */
public class CursoController {
    private List<Curso> cursos;
    

    public CursoController() {
        this.cursos = new ArrayList<>();
    }

    public void addCurso(Curso curso) {
        cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }
    
    public Curso getCursoByI(int i){
        
        return cursos.get(i);
    }
    
    public List<Curso> getCursosByEscuela(String escuela){
        List<Curso> cursosEscuela = new ArrayList<>();
        for(Curso curso : cursos){
            if(curso.getEscArea().getName().equals(escuela)){
                cursosEscuela.add(curso);
            }
        }
        return cursosEscuela;
    }
}
