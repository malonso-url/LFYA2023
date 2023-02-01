using System;
using System.Text.RegularExpressions;

namespace _01_ExpresionesRegulares
{
    class Program
    {
        static void Main(string[] args)
        {
            string opt = "";
            while (!opt.Equals("0")) {
                Console.WriteLine("Ingrese un correo electrónico, o 0 si desea salir");
                opt = Console.ReadLine();

                if (!opt.Equals("0")) {
                    if (Regex.IsMatch(opt, "^(([a-zA-Z0-9]|_|\\.)+@([a-zA-Z0-9]|\\.)+\\.([a-zA-Z0-9])+)$"))
                    {
                        Console.WriteLine("Si es un correo electronico");
                    }
                    else
                    {
                        Console.WriteLine("No es un correo electronico");
                    }
                }
                
            }
            
        }
    }
}
