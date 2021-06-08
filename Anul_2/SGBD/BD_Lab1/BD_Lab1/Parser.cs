using System;
using System.Xml;

namespace BD_Lab1
{
    public class Parser
    {

        XmlElement tablesNode;

        public Parser(XmlDocument doc)
        {
            this.tablesNode = doc.DocumentElement;
        }

        public Table GetParent()
        {
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
            return parent;
        }

        public Table getChild()
        {


            Table child = new Table();
            XmlNode childNode = tablesNode.ChildNodes[1];

            string name = childNode.ChildNodes[0].InnerText;
            child.Name = name;
            int nofields = int.Parse(childNode.ChildNodes[1].InnerText);
            child.Nofields = nofields;
            XmlNode fields = childNode.ChildNodes[2];
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
            return child;
        }

    }
}
