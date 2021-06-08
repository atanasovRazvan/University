using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;

namespace BD_Lab1
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            XmlDocument doc = new XmlDocument();
            doc.Load("C:\\Users\\Razvan\\Desktop\\Facultate\\Anul_2\\SGBD\\BD_Lab1\\BD_Lab1\\config2.xml");
            Parser parser = new Parser(doc);
            XmlElement tablesNode = doc.DocumentElement;

            Table parent = new Table();
            XmlNode parentNode = tablesNode.ChildNodes[0];

            string name = parentNode.ChildNodes[0].InnerText;
            parent.Name = name;
            int nofields = int.Parse(parentNode.ChildNodes[1].InnerText);
            parent.Nofields = nofields;
            XmlNode fields = parentNode.ChildNodes[2];

            for (int i = 0; i < nofields; i++)
            {
                XmlNode f = fields.ChildNodes[i];

                string fname = f.ChildNodes[0].InnerText;
                string stringType = f.ChildNodes[1].InnerText;
                bool isPK = bool.Parse(f.ChildNodes[2].InnerText);
                bool isFK = bool.Parse(f.ChildNodes[3].InnerText);
                Field field = new Field
                {
                    Fname = fname,
                    Type = stringType,
                    IsPK = isPK,
                    IsFK = isFK
                };
                parent.Fields.Add(field);
            }


            Table child = new Table();
            XmlNode childNode = tablesNode.ChildNodes[1];

            name = childNode.ChildNodes[0].InnerText;
            child.Name = name;
            nofields = int.Parse(childNode.ChildNodes[1].InnerText);
            child.Nofields = nofields;
            fields = childNode.ChildNodes[2];

            for (int i = 0; i < nofields; i++)
            {
                XmlNode f = fields.ChildNodes[i];

                string fname = f.ChildNodes[0].InnerText;
                string stringType = f.ChildNodes[1].InnerText;
                bool isPK = bool.Parse(f.ChildNodes[2].InnerText);
                bool isFK = bool.Parse(f.ChildNodes[3].InnerText);
                Field field = new Field
                {
                    Fname = fname,
                    Type = stringType,
                    IsPK = isPK,
                    IsFK = isFK
                };
                child.Fields.Add(field);
            }

            Application.Run(new Form1(parent, child));
        }
    }
}
