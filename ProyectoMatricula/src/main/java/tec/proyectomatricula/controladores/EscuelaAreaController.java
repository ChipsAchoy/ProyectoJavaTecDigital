/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.proyectomatricula.controladores;

import java.util.ArrayList;
import java.util.List;
import tec.proyectomatricula.modelos.EscuelaArea;

/**
 *
 * @author INTEL
 */
public class EscuelaAreaController {
    private List<EscuelaArea> escuelasAreas;
    
    /**
     *
     */
    public EscuelaAreaController() {
        this.escuelasAreas = new ArrayList<>();
    }

    /**
     *
     * @param escArea
     */
    public void addEscuelaArea(EscuelaArea escArea) {
        escuelasAreas.add(escArea);
    }

    /**
     *
     * @return
     */
    public List<EscuelaArea> getEscuelasArea() {
        return escuelasAreas;
    }
    
    /**
     *
     * @param i
     * @return
     */
    public EscuelaArea getCursoByI(int i){
        
        return escuelasAreas.get(i);
    }
    

}
