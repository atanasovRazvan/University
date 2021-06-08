using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using Grpc.Core;
using NetworkingGrpc;
using Bilet = Model.Bilet;
using Spectacol = Model.Spectacol;

namespace ClientGrpc
{
    public partial class Form2 : Form
    {
        private FestivalService.FestivalServiceClient server;
        private IAsyncStreamReader<ServerReply> serverReply;
        private IList<Spectacol> spectacolData;
        
        public Form2(FestivalService.FestivalServiceClient server, IAsyncStreamReader<ServerReply> reply)
        {
            InitializeComponent();
            this.server = server;
            this.serverReply = reply;
            initModel();
            ObserverRead();
            dataText.Text = "dd-mm-yyyy";
            dataText.ForeColor = Color.DarkGray;
        }
        
        private void initModel()
        {
            var reply = server.getAllSpectacol(new GetAllRequest());
            var allProto = reply.AllSpectacole;
            List<Model.Spectacol> allSpectacole = new List<Spectacol>();
            foreach (var spectacol in allProto)
            {
                Model.Spectacol newSpectacol = new Spectacol(spectacol.Id, spectacol.Artist, DateTime.Parse(spectacol.Date), spectacol.Loc, spectacol.LocuriDisp, spectacol.LocuriVandute, spectacol.Ora);
                allSpectacole.Add(newSpectacol);
            }
            spectacolData = new List<Spectacol>(allSpectacole);
            mainDataView.DataSource = spectacolData;
            
        }

        private void searchButton_Click(object sender, EventArgs e)
        {
            initSearchModel(dataText.Text);
        }
        
        private void initSearchModel(string data)
        {
            var reply = server.getSpectacolByDate(new GetAllByDateRequest(){Date = data});
            var allProto = reply.AllSpectacoleByDate;
            List<Model.Spectacol> allSpectacole = new List<Spectacol>();
            foreach (var spectacol in allProto)
            {
                Model.Spectacol newSpectacol = new Spectacol(spectacol.Id, spectacol.Artist, DateTime.Parse(spectacol.Date), spectacol.Loc, spectacol.LocuriDisp, spectacol.LocuriVandute, spectacol.Ora);
                allSpectacole.Add(newSpectacol);
            }
            searchDataView.DataSource = allSpectacole;
        }
        
        private void mainDataView_CellFormatting(object sender, DataGridViewCellFormattingEventArgs e)
        {
                foreach(DataGridViewRow row in mainDataView.Rows)
                {
                    if (Convert.ToInt32(row.Cells[3].Value) == 0)
                        row.DefaultCellStyle.BackColor = Color.Red;
                }
        }

        private void logoutButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void addButton_Click(object sender, EventArgs e)
        {
                string nume = numeText.Text;
                int nr = int.Parse(nrText.Text);

                foreach(DataGridViewRow row in mainDataView.SelectedRows)
                {
                    int spectacolId = int.Parse(row.Cells[6].Value.ToString());
                    DateTime data = DateTime.Parse(row.Cells[1].Value.ToString());
                    string loc = row.Cells[2].Value.ToString();
                    string artist = row.Cells[0].Value.ToString();
                    int disp = int.Parse(row.Cells[3].Value.ToString());
                    int vandute = int.Parse(row.Cells[4].Value.ToString());
                    string ora = row.Cells[5].Value.ToString();

                    Spectacol spectacol = new Spectacol(spectacolId, artist, data, loc, disp, vandute, ora);
                    Bilet bilet = new Bilet(1, nume, spectacolId, nr);
                    AddBilet(bilet);
                }

                numeText.Text = "";
                nrText.Text = "";

        }

        // cat timp e deschisa conexiunea cu serverul se asteapta un raspuns de la server
        // atunci cand se salveaza un bilet pentru a se putea modifica in interfata grafica
        // elementele din tabel
        public async void ObserverRead()
        {
            while (true)
            {
                var all = serverReply.ReadAllAsync();
                await foreach (var repl in all)
                {
                    Console.WriteLine(repl);
                    if (repl.Spectacol != null)
                    {
                        NetworkingGrpc.Spectacol spectacolProto = repl.Spectacol;
                        Model.Spectacol newSpectacol = new Spectacol(spectacolProto.Id, spectacolProto.Artist,DateTime.Parse(spectacolProto.Date), spectacolProto.Loc, spectacolProto.LocuriDisp, spectacolProto.LocuriVandute, spectacolProto.Ora);
                        int pos = 0;
                        Model.Spectacol spectacolToBeDeleted = null;
                        foreach (Spectacol s in spectacolData)
                        {
                            if (s.Id == newSpectacol.Id)
                            {
                                spectacolToBeDeleted = s;
                                break;
                            }
                            else pos++;
                        }
                
                        spectacolData.Remove(spectacolToBeDeleted);
                        spectacolData.Insert(pos, newSpectacol);
                        mainDataView.DataSource = null;
                        mainDataView.DataSource = spectacolData;
                    }
                }
            }
        }
        public async void AddBilet(Bilet bilet)
        {
            NetworkingGrpc.Bilet biletProto = new NetworkingGrpc.Bilet(){Id = bilet.Id, NrLocuri = bilet.NrLocuri, NumeCumparator = bilet.Nume, SpectacolId = bilet.SpectacolId};
            var requests = new List<SaveBiletRequest>
            {
                new SaveBiletRequest(){Bilet = biletProto}
            };
            
            using (var call = server.saveBilet())
            {
                var responseReaderTask = Task.Run(async () =>
                {
                    // var all = serverReply.ReadAllAsync();
                    // await foreach (var repl in all)
                    // {
                    //     Console.WriteLine(repl);
                    //     if (repl.Spectacol != null)
                    //     {
                    //         NetworkingGrpc.Spectacol spectacolProto = repl.Spectacol;
                    //         Model.Spectacol newSpectacol = new Spectacol(spectacolProto.Id, spectacolProto.Artist,DateTime.Parse(spectacolProto.Date), spectacolProto.Loc, spectacolProto.LocuriDisp, spectacolProto.LocuriVandute, spectacolProto.Ora);
                    //         int pos = 0;
                    //         Model.Spectacol spectacolToBeDeleted = null;
                    //         foreach (Spectacol s in spectacolData)
                    //         {
                    //             if (s.Id == newSpectacol.Id)
                    //             {
                    //                 spectacolToBeDeleted = s;
                    //                 break;
                    //             }
                    //             else pos++;
                    //         }
                    //
                    //         spectacolData.Remove(spectacolToBeDeleted);
                    //         spectacolData.Insert(pos, newSpectacol);
                    //         mainDataView.DataSource = null;
                    //         mainDataView.DataSource = spectacolData;
                    //     }
                    // }
                    // while (await serverReply.MoveNext(CancellationToken.None))
                    // {
                    //     var note = serverReply.Current;
                    //     Console.WriteLine("Received " + note);
                    //     NetworkingGrpc.Spectacol spectacolProto = note.Spectacol;
                    //     Model.Spectacol newSpectacol = new Spectacol(spectacolProto.Id, spectacolProto.Artist,DateTime.Parse(spectacolProto.Date), spectacolProto.Loc, spectacolProto.LocuriDisp, spectacolProto.LocuriVandute, spectacolProto.Ora);
                    //     int pos = 0;
                    //     Model.Spectacol spectacolToBeDeleted = null;
                    //     foreach (Spectacol s in spectacolData)
                    //     {
                    //         if (s.Id == newSpectacol.Id)
                    //         {
                    //             spectacolToBeDeleted = s;
                    //             break;
                    //         }
                    //         else pos++;
                    //     }
                    //
                    //     spectacolData.Remove(spectacolToBeDeleted);
                    //     spectacolData.Insert(pos, newSpectacol);
                    //     mainDataView.DataSource = null;
                    //     mainDataView.DataSource = spectacolData;
                    // }
                });

                foreach (SaveBiletRequest request in requests)
                {
                    await call.RequestStream.WriteAsync(request);
                }
                await call.RequestStream.CompleteAsync();
                await responseReaderTask;
            }
            
        }

        private void dataText_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void dataText_MouseClick(object sender, MouseEventArgs e)
        {
            dataText.Text = "";
        }


        private void dataText_Enter(object sender, EventArgs e)
        {
            if (dataText.Text == "yyyy-mm-dd")
            {
                dataText.ForeColor = Color.Black;
                dataText.Text = "";
            }
        }

        private void dataText_Leave(object sender, EventArgs e)
        {
            if (dataText.Text.Length == 0)
            {
                dataText.ForeColor = Color.DarkGray;
                dataText.Text = "yyyy-mm-dd";
            }
        }

        private void mainDataView_CellFormatting_1(object sender, DataGridViewCellFormattingEventArgs e)
        {
            
        }
    }
}
