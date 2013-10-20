このブランチでインストーラーを作りたい
# jenkins playframework plugin

[jenkins](http://jenkins-ci.org/)で使えるいい感じの[Play Framework](http://www.playframework.com/)向けのプラグインが無かったので作ることにした。

## 目指す機能

 - プロジェクト毎に任意のバージョンのPlay Frameworkを利用できる
 - 手動でPlay Frameworkをインストールしなくていい
 - プロジェクトの途中でPlay Frameworkのバージョンを変えるとかできる
 - Play Frameworkのコマンドがそのまま使える

## どうしてこうなった？

 - Play Frameworkは`/usr/local/etc/`とかの"一般ユーザーに書き込み権限が必要な場所にインストールしちゃだめだよ！"って書いてある
 - え！？jenkins用のPlay Frameworkはどこにインストールすればいいの！？
 - pluginつくってインストールする方式にすればなんか許せる気がする

