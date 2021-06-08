using System;
using System.Collections.Generic;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;


namespace SGBDlab2
{

    /** API DOCUMENTATION:
     *  
     *  - table format is: name numberOfFields fields:[pk1 pk2 .. pkn field1 field2 .. fieldx fk1 fk2 .. fkm]
     *      - primary keys MUST ALWAYS be FIRST
     *      - foreign keys MUST ALWAYS be LAST
     *      - any other non-pk/-fk fields can be in any order
     *      
     *  - field format is: name type isPK isFK
     *      - name must be exact the same one from the DB
     *      - type must be the Exact ones the DBType enum uses
     *      - the booleans isPK, isFK must be "true" or "false"
     *      
     *  - the API parses ONLY two Tables from the "config.xml" file.
     *      - the FIRST Table is the PARENT Table
     *      - the SECOND Table is the CHILD Table
     *      - the API is NOT responsible with the faulty formats of the input (e.g. forgot pk-s, forgot fk-s, typos)
     *      
     *  - the API does NOT support different names for the fk-s that reference the pk-s.
     * 
     * 
     *  APP DOCUMENTATION:
     *  
     *  - the App will create on the fly FieldBoxes for the parent and child Fields and put them in a Panel
     *  - the App will know where to find parent and child Fields independently.
     *  - the App can handle creating queries for any number of pk-s, fk-s and Fields.
     *      - a query builder is used to concatenate all the necessary Fields for the execution.
     *      - it is best to know that ALL the pk-s and fk-s are considered INTEGERS - any other types will cause the App to crash.
     * 
     *  - the App does not automatically ignore pk-s or fk-s - you must do it manually:
     *      - e.g. int startIndex = 0 + child.getPK().Count; //avoid considering PK-s
     *      - e.g. int upperLimit = child.Fields.Count - child.getFK().Count; //avoid considering FK-s
     *  - the App supports looking for Fields by their position in their source Table.
     *      - e.g. to get the pk-s from Parent Table, you must
     *              int index = 0;
     *              foreach (Field field in parent.getPK())
     *              {
     *                  getParentTextBoxByNumber(index).Text = dataGridViewParent.CurrentRow.Cells[index].Value.ToString();
     *                  index++;
     *              }
     *  - the App does NOT support getting Fields by their name.
     *  - the App ONLY supports DataTypes that have a ToString() method - in order to show the values in the TextBox-es.
     *  - the App does NOT support displaying tables with a number of Fields larger than 7.
     */

    public partial class Form1 : Form
    {

        Table parent;
        Table child;
        List<TextBox> textInputs = new List<TextBox>();


        private TextBox getParentTextBoxByNumber(int index) // index is between 0 and parent.NoFields-1
        {
            return textInputs[index];
        }

        private TextBox getChildTextBoxByNumber(int index) // index is between 0 and parent.NoFields-1 (NOTA BENE: ALWAYS!!! THE PK-s ARE FIRST)
        {
            return textInputs[parent.Nofields + index]; // map position
        }


        // DA BEES' KNEES.
        private void Gener8()
        {
            int x = 100;
            int y = 0;
            for (int i = 0; i < parent.Nofields; i++)
            {
                Label labeli = new Label
                {
                    Location = new System.Drawing.Point(x-100, y),
                    Name = "labelParent" + i.ToString(),
                    Text = parent.Fields[i].Fname,
                    Size = new System.Drawing.Size(171, 20),
                    TabIndex = 4,
                };


                TextBox textboxi = new TextBox
                {
                    Location = new System.Drawing.Point(x, y),
                    Name = "textBoxParent" + i.ToString(),
                    Size = new System.Drawing.Size(171, 20),
                    TabIndex = 4,
                };
                y += 30;
                this.Controls.Add(textboxi);
                this.textInputs.Add(textboxi);
                this.panel1.Controls.Add(textboxi);
                this.panel1.Controls.Add(labeli);
            }


            x = 500;
            y = 0;
            for (int i = 0; i < child.Nofields; i++)
            {
                Label labeli = new Label
                {
                    Location = new System.Drawing.Point(x-100, y),
                    Name = "labelChild" + i.ToString(),
                    Text = child.Fields[i].Fname,
                    Size = new System.Drawing.Size(171, 20),
                    TabIndex = 4,
                };


                TextBox textboxi = new TextBox
                {
                    Location = new System.Drawing.Point(x, y),
                    Name = "textBoxChild" + i.ToString(),
                    Size = new System.Drawing.Size(171, 20),
                    TabIndex = 4,
                };

                y += 30;
                this.Controls.Add(textboxi);
                this.textInputs.Add(textboxi);
                this.panel1.Controls.Add(textboxi);
                this.panel1.Controls.Add(labeli);
            }
        }



        //SqlConnection connection = new SqlConnection(@"Data Source=DESKTOP-A1S24IB\SQLEXPRESS;Initial Catalog=movie;Integrated Security=True");
        SqlConnection connection = new SqlConnection(@"Data Source=DESKTOP-A1S24IB\SQLEXPRESS;Initial Catalog=labsgbd;Integrated Security=True");
        //SqlConnection connection = new SqlConnection("Data Source=DESKTOP-A1S24IB\\SQLEXPRESS;Initial Catalog=...;Integrated Security=True");
        SqlDataAdapter adapter = new SqlDataAdapter();
        SqlDataAdapter adapter2 = new SqlDataAdapter();
        SqlDataAdapter adapter3 = new SqlDataAdapter();
        DataSet data = new DataSet();
        DataSet data2 = new DataSet();
        DataSet data3 = new DataSet();

        public Form1(Table parent, Table child)
        {
            InitializeComponent();
            this.parent = parent;
            this.child = child;
            dataGridViewParent.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridViewChild.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            Gener8();
        }

        private void button1_Click(object sender, EventArgs e) // - să se afişeze toate înregistrările tabelei părinte;
        {
            //adapter.SelectCommand = new SqlCommand("SELECT * FROM " + parent.Name, connection);

            string queryString = SQLQueryBuilder.SelectCommand(parent);
            adapter.SelectCommand = new SqlCommand(queryString, connection);
            data.Clear();
            adapter.Fill(data);
            dataGridViewParent.DataSource = data.Tables[0];//luam tabelul returnat de query
        }

        private void button2_Click(object sender, EventArgs e) // insert
        { // având selectată o înregistrare din părinte, se permite adăugarea unei noi înregistrări fiu
            try
            {
                //adapter.InsertCommand = new SqlCommand("INSERT INTO " + child.Name + " " + child.GetInsertParamsString() + " VALUES " + child.GetInsertAtParamsString(), connection); //TODO: variable number of insert fields
                string queryString = SQLQueryBuilder.InsertCommand(child);
                adapter.InsertCommand = new SqlCommand(queryString, connection);
                int index = 0 + child.getPK().Count; //avoid considering PK-s
                foreach (Field field in child.getNonPK())
                {
                    Enum.TryParse(field.Type, out DbType type);
                    adapter.InsertCommand.Parameters.Add("@" + field.Fname, type).Value = getChildTextBoxByNumber(index).Text;
                    index += 1;
                }
                connection.Open();
                adapter.InsertCommand.ExecuteNonQuery();
                MessageBox.Show("ADAUGAT!");
            }
            catch (NullReferenceException)
            {
                MessageBox.Show("SELECTATI O INREGISTRARE DIN TABELUL PARINTE!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                connection.Close();
            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e) // incarc toate inregistrarile compatibile din copil
        { // la selectarea unei înregistrări din părinte, se vor afişa toate înregistrările tabelei fiu
            try
            {
                //adapter2.SelectCommand = new SqlCommand("SELECT * FROM " + child.Name + " WHERE " + child.getFK()[0].Fname + " = @cod_director", connection);
                string queryString = SQLQueryBuilder.SelectWhereCommand(child);
                adapter2.SelectCommand = new SqlCommand(queryString, connection);

                //parametrize primary keys number (consider all of them when querying)
                int index = 0;
                foreach(Field pk in parent.getPK()) //NOTA BENE: ALL THE PK-s ARE INT TYPES !!!
                {
                    int value = (int)dataGridViewParent.CurrentRow.Cells[index].Value;
                    adapter2.SelectCommand.Parameters.Add("@"+pk.Fname, SqlDbType.Int).Value = value;
                }
                data2.Clear();
                adapter2.Fill(data2);
                dataGridViewChild.DataSource = data2.Tables[0];//luam tabelul returnat de query


                index = 0;
                foreach (Field field in parent.Fields)
                {
                    getParentTextBoxByNumber(index).Text = dataGridViewParent.CurrentRow.Cells[index].Value.ToString();
                    index++;
                }
            }
            catch (Exception)
            {
                MessageBox.Show("YOU MUST SELECT A ROW FROM THE PARENT TABLE!");
            }
        }

        
        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e) // incarc field-urile copilului
        { // la selectarea unei înregistrări din fiu, trebuie să se permită ştergerea sau actualizarea datelor acesteia
            DataGridViewRow row = dataGridViewChild.CurrentRow;
            if (row.Cells[1].Value is System.DBNull)
                return;
            for(int index=0; index < child.Nofields; index++)
            {
                getChildTextBoxByNumber(index).Text = row.Cells[index].Value.ToString();
            }
        }

        private void button3_Click(object sender, EventArgs e) // sterge
        { // la selectarea unei înregistrări din fiu, trebuie să se permită ştergerea sau actualizarea datelor acesteia
            try
            {
                //adapter.DeleteCommand = new SqlCommand("DELETE FROM " + child.Name + " WHERE " + child.getPK()[0].Fname + " = @cod_film", connection); //TODO: what if multiple pk-s
                string queryString = SQLQueryBuilder.DeleteCommand(child);
                adapter.DeleteCommand = new SqlCommand(queryString, connection);
                int index = 0; // PK-s are ALWAYS FIRST IN ANY TABLE !!!
                foreach (Field pk in child.getPK())
                {
                    adapter.DeleteCommand.Parameters.Add(pk.Fname, SqlDbType.Int).Value = (int)dataGridViewChild.CurrentRow.Cells[index].Value;
                    index += 1;
                }
                connection.Open();
                adapter.DeleteCommand.ExecuteNonQuery();
                MessageBox.Show("STERS!");
            }
            catch (NullReferenceException)
            {
                MessageBox.Show("SELECTATI O INREGISTRARE DIN TABELUL FIU!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                connection.Close();
            }
        }

        private void button4_Click(object sender, EventArgs e) // modifica
        { // la selectarea unei înregistrări din fiu, trebuie să se permită ştergerea sau actualizarea datelor acesteia
            try
            {
                //adapter.UpdateCommand = new SqlCommand("UPDATE " + child.Name + " SET titlu = @titlu ,an_aparitie = @an_aparitie, cod_director = @cod_director WHERE " + child.getPK()[0].Fname + " = @cod_film", connection);
                string queryString = SQLQueryBuilder.UpdateCommand(child);
                adapter.UpdateCommand = new SqlCommand(queryString, connection);
                int index = 0;
                foreach (Field f in child.Fields)
                {
                    Enum.TryParse(f.Type, out DbType type);
                    adapter.UpdateCommand.Parameters.Add("@"+f.Fname, type).Value = getChildTextBoxByNumber(index).Text;
                    index++;
                }
                connection.Open();
                int numberOfRowsAffected = adapter.UpdateCommand.ExecuteNonQuery();
                if(numberOfRowsAffected == 0)
                {
                    MessageBox.Show("NU S-A GASIT INREGISTRAREA!");
                }
                else
                {
                    MessageBox.Show("MODIFICAT!");
                }
            }
            catch (NullReferenceException)
            {
                MessageBox.Show("SELECTATI O INREGISTRARE DIN TABELUL FIU!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                connection.Close();
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
