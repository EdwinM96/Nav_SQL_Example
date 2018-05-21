package com.example.edwin.nav_sql_example.modelo;

public class Nota {
    private String evaluacion;
    private double nota;

    public Nota(String evaluacion, double nota) {
        this.evaluacion = evaluacion;
        this.nota = nota;
    }

    public Nota() {
    }

    @Override
    public String toString() {
        return "Nota{" +
                "nota=" + nota +
                '}';
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
