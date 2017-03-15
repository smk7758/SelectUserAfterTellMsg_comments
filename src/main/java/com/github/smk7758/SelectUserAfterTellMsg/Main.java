package com.github.smk7758.SelectUserAfterTellMsg;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener { // implements Listenerはeventを使う時に必要。
	@Override
	public void onEnable() {
		// TODO ここに、プラグインが有効化された時の処理を実装してください。
		getServer().getPluginManager().registerEvents(this, this); // eventを使う時に必要。
		getLogger().info("＼ｱｯｶﾘｰﾝ／");
	}

	@Override
	public void onDisable() {
		// TODO ここに、プラグインが無効化された時の処理を実装してください。
	}

	// TODO チャット末尾に@id でとりあえず何かしら相手にアクション起こしたい！
	@EventHandler // eventを使う時に必要。
	public void onMessage(AsyncPlayerChatEvent event) { // ()内は前者がイベント(event)名、後者がそれの関数(つまりリンクのようなもの)。
		String message = event.getMessage(); // メッセージを取得。
		String[] message_split = message.split(" "); // メッセージを空白で分割。
		for (String msg : message_split) { // メッセージの集まりを一つ一つのメッセージに分解。
			if (msg.startsWith("@")) { // msgつまり空白の後ろの文章が、@で始まっているのかを確認。
				// msg == @player
				String msg_player_name = msg.substring(1); // プレーヤーの名前が含まれる文章(@minecraftid)から"@"を除去。
				Player player = getServer().getPlayer(msg_player_name); // 上記のものからPlayer型を取得。
				if (player != null) { // その名前のプレーヤーが存在するかの確認。
					// aiue @plyaer
					String message_for_send = message.replaceAll(msg, " "); // イベントから取得したメッセージからプレーヤーを含む文章(@minecraftid)を除去。
					player.sendMessage(message_for_send); // 送信したいプレーヤー(@minecraftidのプレーヤー)にメッセージを送信。
					player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1); //サウンドを流す。
				}
			}
		}
	}
}
