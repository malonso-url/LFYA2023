using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace Analizador_Ejemplo
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Ingrese el nombre del archivo");
            string filename = Console.ReadLine();

            List<string> lineasDeCodigo = new List<string>();
            lineasDeCodigo.Add("public class " + filename + " {");
            lineasDeCodigo.Add("public static void main(String[] args) {");
            lineasDeCodigo.Add("System.out.println(\"Hola Mundo\");");
            lineasDeCodigo.Add("}");



            //Trabajando los set
            string[] sets = { "LETRA='A'..'Z'+'a'..'z'+'_'", "DIGITO  = '0'..'9'", "SIMBOL='%'" };

            lineasDeCodigo.Add("static String identify_SET(char lexeme) {"); //Inicia funcion de SETS
            lineasDeCodigo.Add("int lexeme_value = (int)lexeme;");



            for (int i = 0; i < sets.Length; i++) {
                string nombreSet = sets[i].Split('=')[0].Trim(); ;
                string valorSet = sets[i].Split('=')[1].Trim();

                //Verifico los conjuntos de sets
                string[] conjuntosSet = valorSet.Split('+');

                for (int j = 0; j < conjuntosSet.Length; j++) {

                    if (conjuntosSet[j].Contains(".."))
                    { //Es un conjunto
                        conjuntosSet[j] = conjuntosSet[j].Replace("..", "$");
                        string[] limites = conjuntosSet[j].Split('$');

                        lineasDeCodigo.Add("int " + nombreSet + j + "_INFERIOR = (int)" + limites[0] + ";");
                        lineasDeCodigo.Add("int " + nombreSet + j + "_SUPERIOR = (int)" + limites[1] + ";");

                        lineasDeCodigo.Add("if (lexeme_value >= "+ nombreSet + j + "_INFERIOR  && lexeme_value <= " + nombreSet + j + "_SUPERIOR)");
                        lineasDeCodigo.Add("return \"" + nombreSet + "\";");

                    }
                    else //Es valor unico 
                    {
                        lineasDeCodigo.Add("int " + nombreSet + j + "_ONLY = (int)" + conjuntosSet[j] + ";");

                        lineasDeCodigo.Add("if (lexeme_value == " + nombreSet + j + "_ONLY)");
                        lineasDeCodigo.Add("return \"" + nombreSet + "\";");



                    }

                }


            }

            lineasDeCodigo.Add("return \"\";"); //FInaliza funcion sets

            lineasDeCodigo.Add("}"); //FInaliza funcion sets
             
            lineasDeCodigo.Add("}"); //FInaliza el programa

            // Create an array of strings to write to the file
            string[] lines = lineasDeCodigo.ToArray();




            // Write the array of strings to the file
            File.WriteAllLines(filename + ".java", lines);

            Console.WriteLine("File written successfully.");

            Console.ReadKey();


            

            

        }
    }
}
