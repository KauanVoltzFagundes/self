package br.edu.qi.appdentista;

import java.io.Serializable;

public class Paciente implements Serializable {
    private int id;
    private String nome;
    private byte dia;
    private byte mes;
    private int ano;
    private int idade;

    private String procedimento;
    private String doutor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public byte getDia() {
        return dia;
    }

    public void setDia(byte dia) {
        this.dia = dia;
    }

    public byte getMes() {
        return mes;
    }

    public void setMes(byte mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getDoutor() {
        return doutor;
    }

    public void setDoutor(String doutor) {
        this.doutor = doutor;
    }

    // Esse metodo abaixo eu poderia ter economizado memoria e ter feito usando apenas um if  porem
    // eu usei um else if para ficar melhor de ler e entender o codigo, caso outra pessoa olhe o codigo
    public boolean validarData(){
        if((dia >= 1 && dia <=31) && (mes >=1 && mes <=12) && (ano >=1)){
             if((mes == 2 && dia == 29) && ((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0) || (dia <=28)){
                return true;
            }
            else if ((dia > 30) && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes ==12) || (dia < 31 && mes!= 2)){
                return true;
            }
        }
        return  false;
    }
    public boolean validarIdade(){
        if(idade < 150 && idade > 0){
            return  true;
        }
        return  false;
    }

    @Override
    public String toString() {
        return
                "\n" + "ID: " + this.id + "\n " +
                "Nome: " + this.nome + "\n" +
                "Idade: " + this.idade + "\n" +
                "Data de consulta: " + this.dia + "/" + this.mes + "/" + this.ano + "\n" +
                "Procedimento: " + this.procedimento + "\n" +
                "Doutor: " + this.doutor + "\n";
    }
}
