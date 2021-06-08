using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Model;

namespace FestivalGUI
{
    public partial class MainForm : Form
    {
        //private FestivalController controller;
        //private IList<Spectacol> spectacolData;
        
        //public MainForm(FestivalController controller)
        //{
        //    InitializeComponent();
        //    this.controller = controller;
        //    controller.updateEvent += tableUpdate;
        //    initModel();
        //}

        //private void initModel()
        //{
        //    spectacolData = new List<Spectacol>(controller.GetAllSpectacol());
        //    mainDataView.DataSource = spectacolData;
        //}

        private void MainForm_Load(object sender, EventArgs e)
        {

        }

        private void search_Click(object sender, EventArgs e)
        {
        //    DateTime data = DateTime.Parse(dataSearchText.Text);
        //    initSearchModel(data);
        }

        //private void initSearchModel(DateTime data)
        //{
        //    searchDataView.DataSource = controller.GetAllSpectacolByDate(data);
        //}

        private void mainDataView_CellFormatting(object sender, DataGridViewCellFormattingEventArgs e)
        {
        //    foreach(DataGridViewRow row in mainDataView.Rows)
        //    {
        //        if (Convert.ToInt32(row.Cells[3].Value) == 0)
        //            row.DefaultCellStyle.BackColor = Color.Red;
        //    }
        }

        private void addButton_Click(object sender, EventArgs e)
        {
        //    string nume = numeText.Text;
        //    int nr = int.Parse(locText.Text);

        //    foreach(DataGridViewRow row in mainDataView.SelectedRows)
        //    {
        //        int spectacolId = int.Parse(row.Cells[6].Value.ToString());
        //        DateTime data = DateTime.Parse(row.Cells[1].Value.ToString());
        //        string loc = row.Cells[2].Value.ToString();
        //        string artist = row.Cells[0].Value.ToString();
        //        int disp = int.Parse(row.Cells[3].Value.ToString());
        //        int vandute = int.Parse(row.Cells[4].Value.ToString());
        //        string ora = row.Cells[5].Value.ToString();

        //        Spectacol spectacol = new Spectacol(spectacolId, artist, data, loc, disp, vandute, ora);
        //        Bilet bilet = new Bilet(1, nume, spectacolId, nr);
        //        controller.AddBilet(bilet, spectacol);
        //    }

        //    numeText.Text = "";
        //    locText.Text = "";

        //    // foreach (DataGridViewRow row in searchDataView.SelectedRows)
        //    // {
        //    //     int spectacolId = int.Parse(row.Cells[6].Value.ToString());
        //    //     DateTime data = DateTime.Parse(row.Cells[1].Value.ToString());
        //    //     string loc = row.Cells[2].Value.ToString();
        //    //     string artist = row.Cells[0].Value.ToString();
        //    //     int disp = int.Parse(row.Cells[3].Value.ToString());
        //    //     int vandute = int.Parse(row.Cells[4].Value.ToString());
        //    //     string ora = row.Cells[5].Value.ToString();
        //    //
        //    //     Spectacol spectacol = new Spectacol(spectacolId, artist, data, loc, disp, vandute, ora);
        //    //     Bilet bilet = new Bilet(1, nume, spectacolId, nr);
        //    //     controller.AddBilet(bilet);
        //    //     controller.updateEvent += tableUpdate;
        //    // }

        }

        private void logoutButton_Click(object sender, EventArgs e)
        {
            
        //    this.Close();
        }
        

        private void mainDataView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
        
        //public void tableUpdate(object sender, FestivalEventArgs e)
        //{
        //    if (e.FestivalEvent == FestivalEventType.SpectacolUpdated)
        //    {
        //        Spectacol spectacol = (Spectacol) e.Data;
        //        Spectacol spectacolToBeDeleted = null;
        //        int pos = 0;
        //        foreach (Spectacol s in spectacolData)
        //        {
        //            if (s.Id == spectacol.Id)
        //            {
        //                spectacolToBeDeleted = s;
        //                break;
        //            }
        //            else pos++;
        //        }
                
        //        spectacolData.Remove(spectacolToBeDeleted);
        //        spectacolData.Insert(pos, spectacol);
                
        //        Console.WriteLine("[MainForm] spectacol updated " + spectacol.Id);
        //        mainDataView.BeginInvoke(new UpdateTableCallback(this.updateTable), new Object[]{mainDataView, spectacolData});
        //        //spectacolData.BeginInvoke((Action) delegate { friendList.DataSource = friendsData; })
                
        //    }
        //}

        ////1. define a method for updating the DataView
        //private void updateTable(DataGridView table, List<Spectacol> newData)
        //{
        //    table.DataSource = null;
        //    table.DataSource = newData;
        //}
        
        //public delegate void UpdateTableCallback(DataGridView table, List<Spectacol> data);
        
        
    }
}
