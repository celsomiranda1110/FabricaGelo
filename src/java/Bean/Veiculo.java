/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author celso
 */
public class Veiculo implements Bean{
    private int IdVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String ano;

    public int getIdVeiculo() {
        return IdVeiculo;
    }

    public void setIdVeiculo(int IdVeiculo) {
        this.IdVeiculo = IdVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
    
    public void replicar(Veiculo _veiculo)
    {
        _veiculo.setIdVeiculo(this.IdVeiculo);
        _veiculo.setPlaca(this.placa);
        _veiculo.setMarca(this.marca);
        _veiculo.setModelo(this.modelo);
        _veiculo.setAno(this.ano);
    }
    
    public String toString()
    {
        return this.marca;
    }
}
