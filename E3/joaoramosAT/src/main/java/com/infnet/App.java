package com.infnet;


public class App 
{
    public static void main( String[] args )
    {
        ViaCepSercice api = new ViaCepSercice();
        try{
            Endereco end = api.consultaPorCep("01001000");
            //String end = api.consultaPorEndereco("SP", "São Paulo","Avenida Paulista").toString();
            System.out.println(end.toString());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("\n\n    === DONE ===");
        
    }
}

