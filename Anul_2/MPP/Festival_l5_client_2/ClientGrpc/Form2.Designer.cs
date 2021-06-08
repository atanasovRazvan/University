namespace ClientGrpc
{
    partial class Form2
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.mainDataView = new System.Windows.Forms.DataGridView();
            this.searchDataView = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.searchButton = new System.Windows.Forms.Button();
            this.logoutButton = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.numeText = new System.Windows.Forms.TextBox();
            this.dataText = new System.Windows.Forms.TextBox();
            this.nrText = new System.Windows.Forms.TextBox();
            this.addButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize) (this.mainDataView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize) (this.searchDataView)).BeginInit();
            this.SuspendLayout();
            // 
            // mainDataView
            // 
            this.mainDataView.ColumnHeadersHeightSizeMode =
                System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.mainDataView.Location = new System.Drawing.Point(12, 12);
            this.mainDataView.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.mainDataView.Name = "mainDataView";
            this.mainDataView.RowTemplate.Height = 28;
            this.mainDataView.Size = new System.Drawing.Size(956, 268);
            this.mainDataView.TabIndex = 0;
            this.mainDataView.CellFormatting +=
                new System.Windows.Forms.DataGridViewCellFormattingEventHandler(this.mainDataView_CellFormatting);
            // 
            // searchDataView
            // 
            this.searchDataView.ColumnHeadersHeightSizeMode =
                System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.searchDataView.Location = new System.Drawing.Point(247, 298);
            this.searchDataView.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.searchDataView.Name = "searchDataView";
            this.searchDataView.RowTemplate.Height = 28;
            this.searchDataView.Size = new System.Drawing.Size(953, 241);
            this.searchDataView.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(42, 312);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(171, 22);
            this.label1.TabIndex = 2;
            this.label1.Text = "Cautare spectacol:";
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(46, 358);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(100, 22);
            this.label2.TabIndex = 3;
            this.label2.Text = "Data:";
            // 
            // searchButton
            // 
            this.searchButton.Location = new System.Drawing.Point(61, 428);
            this.searchButton.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.searchButton.Name = "searchButton";
            this.searchButton.Size = new System.Drawing.Size(109, 35);
            this.searchButton.TabIndex = 4;
            this.searchButton.Text = "Cautare";
            this.searchButton.UseVisualStyleBackColor = true;
            this.searchButton.Click += new System.EventHandler(this.searchButton_Click);
            // 
            // logoutButton
            // 
            this.logoutButton.Location = new System.Drawing.Point(12, 518);
            this.logoutButton.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.logoutButton.Name = "logoutButton";
            this.logoutButton.Size = new System.Drawing.Size(84, 41);
            this.logoutButton.TabIndex = 5;
            this.logoutButton.Text = "Logout";
            this.logoutButton.UseVisualStyleBackColor = true;
            this.logoutButton.Click += new System.EventHandler(this.logoutButton_Click);
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(988, 31);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(137, 28);
            this.label3.TabIndex = 6;
            this.label3.Text = "Adaugare bilet:";
            // 
            // label4
            // 
            this.label4.Location = new System.Drawing.Point(988, 84);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(74, 22);
            this.label4.TabIndex = 7;
            this.label4.Text = "Nume:";
            // 
            // label5
            // 
            this.label5.Location = new System.Drawing.Point(988, 134);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(100, 22);
            this.label5.TabIndex = 8;
            this.label5.Text = "Nr locuri:";
            // 
            // numeText
            // 
            this.numeText.Location = new System.Drawing.Point(1068, 81);
            this.numeText.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.numeText.Name = "numeText";
            this.numeText.Size = new System.Drawing.Size(100, 31);
            this.numeText.TabIndex = 9;
            // 
            // dataText
            // 
            this.dataText.Location = new System.Drawing.Point(113, 358);
            this.dataText.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dataText.Name = "dataText";
            this.dataText.Size = new System.Drawing.Size(100, 31);
            this.dataText.TabIndex = 10;
            this.dataText.MouseClick += new System.Windows.Forms.MouseEventHandler(this.dataText_MouseClick);
            this.dataText.TextChanged += new System.EventHandler(this.dataText_TextChanged);
            this.dataText.Enter += new System.EventHandler(this.dataText_Enter);
            this.dataText.Leave += new System.EventHandler(this.dataText_Leave);
            // 
            // nrText
            // 
            this.nrText.Location = new System.Drawing.Point(1068, 131);
            this.nrText.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.nrText.Name = "nrText";
            this.nrText.Size = new System.Drawing.Size(100, 31);
            this.nrText.TabIndex = 11;
            // 
            // addButton
            // 
            this.addButton.Location = new System.Drawing.Point(1029, 198);
            this.addButton.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(96, 36);
            this.addButton.TabIndex = 12;
            this.addButton.Text = "Adauga";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1212, 562);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.nrText);
            this.Controls.Add(this.dataText);
            this.Controls.Add(this.numeText);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.logoutButton);
            this.Controls.Add(this.searchButton);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.searchDataView);
            this.Controls.Add(this.mainDataView);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form2";
            this.Text = "Gestionare bilete";
            ((System.ComponentModel.ISupportInitialize) (this.mainDataView)).EndInit();
            ((System.ComponentModel.ISupportInitialize) (this.searchDataView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion

        private System.Windows.Forms.DataGridView mainDataView;
        private System.Windows.Forms.DataGridView searchDataView;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button searchButton;
        private System.Windows.Forms.Button logoutButton;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.TextBox nrText;
        private System.Windows.Forms.TextBox dataText;
        private System.Windows.Forms.TextBox numeText;
    }
}

