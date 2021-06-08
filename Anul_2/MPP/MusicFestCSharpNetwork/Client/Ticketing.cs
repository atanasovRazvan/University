using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using Model;
using MusicFestCSharpNetwork;
using Networking;
using Services;

namespace MusicFest
{
    public partial class Ticketing : Form, IObserver
    {
        private readonly IServices server;
        private User currentUser;

        public Ticketing(IServices server)
        {
            this.server = server;
            InitializeComponent();
            currentUser = null;
        }
        public void initData(List<Show> shows)
        {
            dataGridView1.Rows.Clear();
            foreach (Show s in shows)
            {
                var index = dataGridView1.Rows.Add();
                dataGridView1.Rows[index].Cells["artistName"].Value = s.ArtistName;
                dataGridView1.Rows[index].Cells["idShow"].Value = s.Id;
                dataGridView1.Rows[index].Cells["date"].Value = s.DateOfShow.Date;
                dataGridView1.Rows[index].Cells["venue"].Value = s.Venue;
                dataGridView1.Rows[index].Cells["remainingTickets"].Value = s.RemainingTickets;
                dataGridView1.Rows[index].Cells["soldTickets"].Value = s.TotalTickets - s.RemainingTickets;
                if (s.RemainingTickets == 0)
                    dataGridView1.Rows[index].DefaultCellStyle.BackColor = ColorTranslator.FromHtml("#BF2A36");
            }
        }

        private void updateTable(DataGridView table, IList<Show> data)
        {
            table.Rows.Clear();
            foreach (Show s in data)
            {
                var index = table.Rows.Add();
                table.Rows[index].Cells["artistName"].Value = s.ArtistName;
                table.Rows[index].Cells["idShow"].Value = s.Id;
                table.Rows[index].Cells["date"].Value = s.DateOfShow.Date;
                table.Rows[index].Cells["venue"].Value = s.Venue;
                table.Rows[index].Cells["remainingTickets"].Value = s.RemainingTickets;
                table.Rows[index].Cells["soldTickets"].Value = s.TotalTickets - s.RemainingTickets;
                if (s.RemainingTickets == 0)
                    dataGridView1.Rows[index].DefaultCellStyle.BackColor = ColorTranslator.FromHtml("#BF2A36");
            }
        }
        
        public delegate void UpdateCallback(DataGridView table, IList<Show> data);
        
        private void search_Click(object sender, EventArgs e)
        {
            Console.WriteLine("requesting shows for date {0}", date);
            List<Show> shows = server.getShowByDate(dateTimePicker1.Value.Date);
            if (shows.Count == 0)
            {
                MessageBox.Show("Nu s-a gasit niciun spectacol pentru data specificata");
                return;
            }
            SearchResult searchResult = new SearchResult(shows);
            searchResult.ShowDialog();
        }
        
        private void buy_Click(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count == 0)
            {
                MessageBox.Show("Nu ati selectat spectacolul");
                return;
            }
            else
            {
                if (nameBox.Text == "")
                {
                    MessageBox.Show("Nu ati introdus numele");
                    return;
                }
                else
                {
                    int index = dataGridView1.SelectedRows[0].Index;
                    if ((int)dataGridView1.Rows[index].Cells["remainingTickets"].Value==0)
                    {
                        MessageBox.Show("Nu se mai pot cumpara bilete pentru acest spectacol");
                        return;
                    }
                    else
                    {
                        Console.WriteLine("requesting buy");
                        server.buyTicket(dataGridView1.Rows[index].Cells["idShow"].Value.ToString(),
                            nameBox.Text,(int)numericUpDown1.Value);
                        dataGridView1.ClearSelection();
                    }
                }
            }
        }
    
        private void dataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (dataGridView1.Rows[e.RowIndex].Cells["remainingTickets"].Value == null ||
                Int32.Parse(dataGridView1.Rows[e.RowIndex].Cells["remainingTickets"].Value.ToString())==0)
            {
                numericUpDown1.Maximum = 0;
                numericUpDown1.Minimum = 0;
                return;
            }

            numericUpDown1.Minimum = 1;
            numericUpDown1.Maximum = (int) dataGridView1.Rows[e.RowIndex].Cells["remainingTickets"].Value;
        }
    
        private void logOut_Click_1(object sender, EventArgs e)
        {
            Console.WriteLine(currentUser.Username+" logging out");
            server.logout(currentUser, this);
            currentUser = null;
            Application.Exit();
        }
        
        public void login(String username, String password){
            server.login(username, password,this);
            Console.WriteLine("Login succeeded ....");
            currentUser = new User(username,password);
            Console.WriteLine("Current user {0}", username);
        }

        public List<Show> getAllShows()
        {
            Console.WriteLine("Requesting shows");
            return server.getAllShows();
        }

        public void soldTicketsUpdate(List<Show> shows)
        {
            System.Console.WriteLine("notifying window");
            dataGridView1.Invoke(new UpdateCallback(this.updateTable), new Object[] {this.dataGridView1, shows});
        }
    }
}