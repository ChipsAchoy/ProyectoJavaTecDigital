/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.proyectomatricula.controladores;

import java.util.ArrayList;
import java.util.List;
import tec.proyectomatricula.modelos.PlanEstudios;

/**
 *
 * @author INTEL
 */
public class PlanEstudiosController {
    private List<PlanEstudios> planEstudios;
    
    /**
     *
     */
    public PlanEstudiosController() {
        this.planEstudios = new ArrayList<>();
    }

    /**
     *
     * @param planEstudio
     */
    public void addPlanEstudio(PlanEstudios planEstudio) {
        planEstudios.add(planEstudio);
    }   

    /**
     *
     * @return
     */
    public List<PlanEstudios> getPlanEstudios() {
        return planEstudios;
    }

    /**
     *
     * @param i
     * @return
     */
    public PlanEstudios getPlanByI(int i){
        
        return planEstudios.get(i);
    }
}
