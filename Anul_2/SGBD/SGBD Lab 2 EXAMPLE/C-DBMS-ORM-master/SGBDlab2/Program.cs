using System;
using System.Windows.Forms;
using System.Xml;


namespace SGBDlab2
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


            Api api = new Api();
            XmlDocument doc = new XmlDocument();
            doc.Load("C:\\Users\\Razvan\\Desktop\\Facultate\\Anul_2\\SGBD\\SGBD Lab 2 EXAMPLE\\C-DBMS-ORM-master\\SGBDlab2\\config.xml");

            //FirstChild is  the root "tables"

            XmlElement tablesNode = doc.DocumentElement; // tables node

            Table parent = new Table();
            XmlNode parentNode = tablesNode.ChildNodes[0];

            string name = parentNode.ChildNodes[0].InnerText; // DIRECTOR
            parent.Name = name;
            int nofields = int.Parse(parentNode.ChildNodes[1].InnerText); // 3
            parent.Nofields = nofields;
            XmlNode fields = parentNode.ChildNodes[2]; // fields node (contains 'nofields' values)

            for (int i = 0; i < nofields; i++)
            {
                XmlNode f = fields.ChildNodes[i];

                string fname = f.ChildNodes[0].InnerText;
                string stringType = f.ChildNodes[1].InnerText;
                //Enum.TryParse(stringType, out DataTypeEnum type); what is love. baby dont hurt me(x2). no more.
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
            //////Table parent = api.GetParent();


            Table child = new Table();
            XmlNode childNode = tablesNode.ChildNodes[1];

            name = childNode.ChildNodes[0].InnerText; // DIRECTOR
            child.Name = name;
            nofields = int.Parse(childNode.ChildNodes[1].InnerText); // 3
            child.Nofields = nofields;
            fields = childNode.ChildNodes[2]; // fields node (contains 'nofields' values)

            for (int i = 0; i < nofields; i++)
            {
                XmlNode f = fields.ChildNodes[i];

                string fname = f.ChildNodes[0].InnerText;
                string stringType = f.ChildNodes[1].InnerText;
                //Enum.TryParse(stringType, out DataTypeEnum type);
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
            //////Table child = api.getChild();

            //WARNING: for some reason, the app will not perform well if the api instance is used, so I used the code in-place.
            Application.Run(new Form1(parent, child));
        }
    }
}
