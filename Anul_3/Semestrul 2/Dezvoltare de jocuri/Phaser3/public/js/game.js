
var preGameScene = {
    preload: greetingPreload,
    create: greetingCreate,
    update: greetingUpdate,
}

var gameScene = {
    preload: preload,
    create: create,
    update: update
}

var preGameConfig = {
    type: Phaser.AUTO,
    width: 1000,
    height: 600,
    physics: {
        default: 'arcade',
        arcade: {
            debug: false,
            gravity: { y: 0 }
        },
    },
    scene: {
        preload: preGameScene.preload,
        create: preGameScene.create,
        update: preGameScene.update
    }
};

var gameConfig = {
    type: Phaser.AUTO,
    width: 1000,
    height: 600,
    physics: {
        default: 'arcade',
        arcade: {
            debug: false,
            gravity: { y: 0 }
        },
    },
    scene: {
        preload: gameScene.preload,
        create: gameScene.create,
        update: gameScene.update
    }
};

var game = new Phaser.Game(preGameConfig);

function greetingPreload(){
    this.load.image('background', 'assets/background.png');
}

function greetingCreate(){
    this.add.image(500, 300, 'background');
    this.startGameText = this.add.text(230, 300, '', {
        fontSize: '32px',
        fill: 'yellow',
        backgroundColor: 'black'
    });
    this.startGameText.setText('Press ANY ARROW to join the game');
    this.cursors = this.input.keyboard.createCursorKeys();
}

function greetingUpdate(){
    if(this.cursors.left.isDown || this.cursors.right.isDown || this.cursors.up.isDown || this.cursors.down.isDown){
        game.destroy(true);
        game = new Phaser.Game(gameConfig);
    }
}

function preload() {
    this.load.image('playerModel', 'assets/mike.png');
    this.load.image('otherPlayer', 'assets/mike.png');
    this.load.image('jerry', 'assets/jerry.png');
    this.load.image('fakeJerry', 'assets/fakeJerry.png');
    this.load.image('fakeJerry2', 'assets/fakeJerry2.png');
}

function create() {
    var self = this;
    this.socket = io();
    this.otherPlayers = this.physics.add.group();

    this.anims.create({
        key: 'jerrys',
        frames: [
            { key: 'jerry' },
            { key: 'fakeJerry' },
            { key: 'fakeJerry2' },
        ],
        frameRate: 2,
        repeat: 1000
    });

    this.socket.on('currentPlayers', function (players) {
        Object.keys(players).forEach(function (id) {
            if (players[id].playerId === self.socket.id) {
                addPlayer(self, players[id]);
            } else {
                addOtherPlayers(self, players[id]);
            }
        });
    });

    this.socket.on('newPlayer', function (playerInfo) {
        addOtherPlayers(self, playerInfo);
    });

    this.socket.on('playerDisconnected', function (playerId) {
        self.otherPlayers.getChildren().forEach(function (otherPlayer) {
            if (playerId === otherPlayer.playerId) {
                otherPlayer.destroy();
            }
        });
    });

    this.socket.on('playerMoved', function (playerInfo) {
        self.otherPlayers.getChildren().forEach(function (otherPlayer) {
            if (playerInfo.playerId === otherPlayer.playerId) {
                otherPlayer.setRotation(playerInfo.rotation);
                otherPlayer.setPosition(playerInfo.x, playerInfo.y);
            }
        });
    });
    this.cursors = this.input.keyboard.createCursorKeys();

    this.blueScoreText = this.add.text(16, 16, '', { fontSize: '32px', fill: '#0000FF' });
    this.redScoreText = this.add.text(816, 16, '', { fontSize: '32px', fill: '#FF0000' });

    this.socket.on('scoreUpdate', function (scores) {
        self.blueScoreText.setText('Blue: ' + scores.blue);
        self.redScoreText.setText('Red: ' + scores.red);
    });

    this.socket.on('jerryLocation', function (jerryLocation) {
        if (self.jerry) self.jerry.destroy();
        self.jerry = self.physics.add.sprite(jerryLocation.x, jerryLocation.y, 'jerry').play('jerrys');
        self.physics.add.overlap(self.mike, self.jerry, function () {
            this.socket.emit('jerryCollected');
        }, null, self);
    });
}

function update() {

    if (this.mike) {

        this.mike.setCollideWorldBounds(true);

        // emit player movement
        var x = this.mike.x;
        var y = this.mike.y;
        var r = this.mike.rotation;
        if (this.mike.oldPosition && (x !== this.mike.oldPosition.x || y !== this.mike.oldPosition.y || r !== this.mike.oldPosition.rotation)) {
            this.socket.emit('playerMovement', { x: this.mike.x, y: this.mike.y, rotation: this.mike.rotation });
        }

        // save old position data
        this.mike.oldPosition = {
            x: this.mike.x,
            y: this.mike.y,
            rotation: this.mike.rotation
        };

        if (this.cursors.left.isDown) {
            this.mike.setAngularVelocity(-300);
        } else if (this.cursors.right.isDown) {
            this.mike.setAngularVelocity(300);
        } else {
            this.mike.setAngularVelocity(0);
        }

        if (this.cursors.up.isDown) {
            this.physics.velocityFromRotation(this.mike.rotation + 4.5, 100, this.mike.body.acceleration);
        } else if(this.cursors.down.isDown){
            this.physics.velocityFromRotation(this.mike.rotation + 1.5, 100, this.mike.body.acceleration);
        } else{
            this.mike.setAcceleration(0);
        }
    }

}

function addPlayer(self, playerInfo) {
    self.mike = self.physics.add
        .image(playerInfo.x, playerInfo.y, 'playerModel')
        .setOrigin(0.5, 0.5)
        .setDisplaySize(100, 100);
    if (playerInfo.team === 'blue') {
        self.mike.setTint(0x0000ff);
    } else {
        self.mike.setTint(0xff0000);
    }
    self.mike.setDrag(200);
    self.mike.setAngularDrag(200);
    self.mike.setMaxVelocity(200);
}

function addOtherPlayers(self, playerInfo) {
    const otherPlayer = self.add
        .sprite(playerInfo.x-50, playerInfo.y+50, 'otherPlayer')
        .setOrigin(0.5, 0.5)
        .setDisplaySize(100, 100);
    if (playerInfo.team === 'blue') {
        otherPlayer.setTint(0x0000ff);
    } else {
        otherPlayer.setTint(0xff0000);
    }
    otherPlayer.playerId = playerInfo.playerId;
    self.otherPlayers.add(otherPlayer);
}