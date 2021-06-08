using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Model;

namespace MusicFest
{
    public partial class SearchResult : Form
    {
        public SearchResult(List<Show> shows)
        {
            InitializeComponent();
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

        private void back_Click(object sender, EventArgs e)
        {
            this.Hide();
            this.Dispose();
        }
    }
}