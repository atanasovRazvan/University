using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace BD_Lab1
{
    public partial class Form1 : Form
    {

        SqlConnection connection = new SqlConnection("Server=DESKTOP-RC1TD1I\\SQLEXPRESS;Database=Store;Integrated Security=true");
        SqlDataAdapter adapter = new SqlDataAdapter();
        SqlDataAdapter adapter2 = new SqlDataAdapter();
        DataSet dataset1 = new DataSet();
        DataSet dataset2 = new DataSet();
        Table parent, child;
        List<TextBox> lista = new List<TextBox>();


        public Form1(Table parent, Table child)
        {
            this.parent = parent;
            this.child = child;
            InitializeComponent();
            lista.Add(textBox1);
            lista.Add(textBox2);
            lista.Add(textBox3);
            lista.Add(textBox4);
            lista.Add(textBox5);
        }


        private void onSelectedShowChilds(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                adauga.Enabled = true;
                adapter.SelectCommand = new SqlCommand("SELECT * FROM " + child.Name + " A WHERE A." + child.Fields[child.Fields.Count-1].Fname + " = @oid;", connection);
                String oid = dataGridView1.Rows[e.RowIndex].Cells[0].FormattedValue.ToString();
                adapter.SelectCommand.Parameters.Add("@oid", SqlDbType.Int).Value = oid;
                dataset2.Clear();
                connection.Close();
                connection.Open();
                adapter.SelectCommand.ExecuteNonQuery();
                adapter.Fill(dataset2);
                dataGridView2.DataSource = dataset2.Tables[0];
                connection.Close();
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                connection.Close();
            }
        }


        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                adapter.SelectCommand = new SqlCommand("SELECT * FROM " + parent.Name, connection);
                dataset1.Clear();
                adapter.Fill(dataset1);
                dataGridView1.DataSource = dataset1.Tables[0];
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        private void cellClick(object sender, DataGridViewCellEventArgs e)
        {
            onSelectedShowChilds(sender, e);
        }

        private void onSelectedInChild(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                update.Enabled = true;
                delete.Enabled = true;
                for(int i=0; i<lista.Count; i++)
                {
                    lista[i].Text = dataGridView2.Rows[e.RowIndex].Cells[i].FormattedValue.ToString();
                }
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
        }

        private void delete_Click(object sender, EventArgs e)
        {
            try
            {
                StringBuilder cmd = new StringBuilder("DELETE FROM " + child.Name + " WHERE " + child.Fields[0].Fname + " = @p");
                adapter.DeleteCommand = new SqlCommand(cmd.ToString(), connection);
                adapter.DeleteCommand.Parameters.Add("@p", SqlDbType.Int).Value = Int32.Parse(textBox1.Text);
                connection.Open();
                adapter.DeleteCommand.ExecuteNonQuery();
                connection.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                connection.Close();
            }
        }

        private void update_Click(object sender, EventArgs e)
        {
            try
            {
                StringBuilder cmd = new StringBuilder("UPDATE " + child.Name + " SET ");
                int nr = 0;
                foreach(Field f in child.Fields)
                {
                    if (nr == 0) nr++;
                    else
                    {
                        cmd.Append(f.Fname);
                        cmd.Append("=@p");
                        cmd.Append(nr);
                        nr++;
                        cmd.Append(", ");
                    }
                }
                cmd.Length -= 2;
                cmd.Append(" WHERE " + child.Fields[0].Fname + " = @p");

                adapter.UpdateCommand = new SqlCommand(cmd.ToString(), connection);

                nr = 0;
                foreach (Field f in child.Fields)
                {
                    if (nr == 0) nr++;
                    else
                    {
                        StringBuilder p = new StringBuilder("@p");
                        p.Append(nr);
                        switch (f.Type)
                        {
                            case "Int": adapter.UpdateCommand.Parameters.Add(p.ToString(), SqlDbType.Int).Value = Int32.Parse(lista[nr].Text); break;
                            case "VarChar": adapter.UpdateCommand.Parameters.Add(p.ToString(), SqlDbType.VarChar).Value = lista[nr].Text; break;
                        }
                        nr++;
                    }
                }

                adapter.UpdateCommand.Parameters.Add("@p", SqlDbType.Int).Value = Int32.Parse(textBox1.Text);
                connection.Open();
                adapter.UpdateCommand.ExecuteNonQuery();
                connection.Close();
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                connection.Close();
            }
        }

        private void adauga_Click(object sender, EventArgs e)
        {
            try
            {
                {

                    StringBuilder cmd = new StringBuilder("INSERT INTO " + child.Name + "(");
                    int nr = 0;
                    foreach (Field f in child.Fields)
                    {
                        if (nr == 0) nr++;
                        else
                        {
                            cmd.Append(f.Fname);
                            nr++;
                            cmd.Append(", ");
                        }
                    }
                    cmd.Length -= 2;
                    cmd.Append(") VALUES(");
                    for (int i = 1; i < nr; i++)
                    {
                        StringBuilder p = new StringBuilder("@p");
                        p.Append(i);
                        p.Append(", ");
                        cmd.Append(p);
                    }
                    cmd.Length -= 2;
                    cmd.Append(");");

                    adapter.InsertCommand = new SqlCommand(cmd.ToString(), connection);

                    nr = 0;
                    foreach (Field f in child.Fields)
                    {
                        if (nr == 0) nr++;
                        else
                        {
                            StringBuilder p = new StringBuilder("@p");
                            p.Append(nr);
                            switch (f.Type)
                            {
                                case "Int": adapter.InsertCommand.Parameters.Add(p.ToString(), SqlDbType.Int).Value = Int32.Parse(lista[nr].Text); break;
                                case "VarChar": adapter.InsertCommand.Parameters.Add(p.ToString(), SqlDbType.VarChar).Value = lista[nr].Text; break;
                            }
                            nr++;
                        }
                    }
                    connection.Open();
                    adapter.InsertCommand.ExecuteNonQuery();
                    connection.Close();
                }
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                connection.Close();
            }
        }
    }
}
