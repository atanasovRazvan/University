using System.Collections.Generic;
using System.Windows.Forms;


namespace SGBDlab2
{
    /**
        THE FORMAT OF THE XML FILE IS THE FOLLOWING:
        <tables>
            <parent>
            </parent>
            
            <child>
            </child>
        </tables>

        THE PARENT AND THE CHILD HAVE THE FOLLOWING STRUCTURE:
        <parent>
            <name></name>
            <nofields></nofields>
            
            <fields>
            </fields>

        </parent>

        THE FIELDS HAVE THE FOLLOWING STRUCTURE:
        <fields>
            ...
            <field>
                <fname></fname>
                <type></type>
                <isPK></isPK>
                <isFK></isFK>
            </field>
            ...
        </fields>
         */


    public class Field
    {
        public string Fname { get; set; }
        public string Type { get; set; }
        public bool IsPK { get; set; }
        public bool IsFK { get; set; }
    }

    public class Table
    {
        public Table()
        {
            Fields = new List<Field>();
        }

        public string Name { get; set; }
        public int Nofields { get; set; }
        public List<Field> Fields { get; set; }

        public List<Field> getPK() //TODO: could be optimised using memoisation
        {
            List<Field> pks = new List<Field>();
            foreach (Field f in this.Fields)
            {
                if (f.IsPK)
                    pks.Add(f);
            }
            return pks;
        }

        public List<Field> getNonPK() //TODO: could be optimised using memoisation
        {
            List<Field> pks = new List<Field>();
            foreach (Field f in this.Fields)
            {
                if (!f.IsPK)
                    pks.Add(f);
            }
            return pks;
        }

        public string GetPKQuery() // table.getPK()[i].Fname + " = @table.getPK()[i].Fname"
        {
            List<Field> pks = this.getPK();
            string returnString = "";// parametrised PK equality condition for composed PK-s
            for (int i = 0; i < pks.Count - 1; i++)
            {
                returnString += pks[i].Fname + " = @" + pks[i].Fname + " AND ";
            }
            returnString += pks[pks.Count - 1].Fname + " = @" + pks[pks.Count - 1].Fname;
            return returnString;
        }

        public List<Field> getFK() //TODO: could be optimised using memoisation
        {
            List<Field> fks = new List<Field>();
            foreach (Field f in this.Fields)
            {
                if (f.IsFK)
                    fks.Add(f);
            }
            return fks;
        }

        public string GetFKQuery() // table.getFK()[i].Fname + " = @table.getFK()[i].Fname"
        {
            List<Field> fks = this.getFK();
            string returnString = "";// parametrised FK equality condition for composed FK-s
            for (int i = 0; i < fks.Count - 1; i++)
            {
                returnString += fks[i].Fname + " = @" + fks[i].Fname + " AND ";
            }
            returnString += fks[fks.Count - 1].Fname + " = @" + fks[fks.Count - 1].Fname;
            return returnString;
        }

        public string GetInsertParamsString()
        {
            string returnString = "(";
            for (int i = 0; i < Nofields - 1; i++)
            {
                if (!this.Fields[i].IsPK) // we will only choose non-PK fields
                {
                    returnString += this.Fields[i].Fname + ",";
                }
            }
            returnString += this.Fields[Nofields - 1].Fname + ")";
            return returnString;
        }

        public string GetInsertAtParamsString()
        {
            string returnString = "(";
            for (int i = 0; i < Nofields - 1; i++)
            {
                if (!this.Fields[i].IsPK) // we will only choose non-PK fields
                {
                    returnString += "@" + this.Fields[i].Fname + ",";
                }
            }
            returnString += "@" + this.Fields[Nofields - 1].Fname + ")";
            return returnString;
        }

        public string GetUpdateParamsQuery()
        {
            string returnString = "";
            for (int i = 0; i < Nofields - 1; i++)
            {
                if (!this.Fields[i].IsPK) // we will only choose non-PK fields
                {
                    returnString += this.Fields[i].Fname + " = @" + this.Fields[i].Fname + ",";
                }
            }
            returnString += this.Fields[Nofields - 1].Fname + " = @" + this.Fields[Nofields - 1].Fname;
            return returnString;
        }
    }


    class SQLQueryBuilder
    {
        public static string SelectCommand(Table table)
        {
            return "SELECT * FROM " + table.Name;
        }


        //public static void setSourcesForSelectWhere()
        public static string SelectWhereCommand(Table table)
        {
            List<Field> fks = table.getFK();
            //return "SELECT * FROM " + table.Name + " WHERE " + table.getFK()[0].Fname + " = @cod_director" + ...; // variable number of fk-s
            return "SELECT * FROM " + table.Name + " WHERE " + table.GetFKQuery();

        }

        public static string InsertCommand(Table table)
        {
            return "INSERT INTO " + table.Name + " " + table.GetInsertParamsString() + " VALUES " + table.GetInsertAtParamsString();
        }

        public static string UpdateCommand(Table table)
        {
            //return "UPDATE " + table.Name + " SET titlu = @titlu ,an_aparitie = @an_aparitie, cod_director = @cod_director WHERE " + table.getPK()[0].Fname + " = @cod_film";
            return "UPDATE " + table.Name + " SET " + table.GetUpdateParamsQuery() + " WHERE " + table.GetPKQuery();
        }

        public static string DeleteCommand(Table table)
        {
            //return "DELETE FROM " + table.Name + " WHERE " + table.getPK()[0].Fname + " = @cod_director";
            return "DELETE FROM " + table.Name + " WHERE " + table.GetPKQuery();
        }
    }
}
