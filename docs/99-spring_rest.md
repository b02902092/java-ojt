# Spring BootとSpring Frameworkの基本
## Spring, Spring FrameworkとSpring Bootの違い
### Spring Framework
- Java開発の現場を席捲したオープンソースのフレームワーク。
    - 当時のJ2EEが重く・書きにくく・テストしにくかったため。
- 2002年作成、2003年OSS化、2004年に1.0リリース~~（随分歴史のあるフレームワーク使ってはりますなぁ～って親父に嫌味を言われた）~~。
    - 今に至っては、巨大なフレームワークの集合体。
- DIとIoCコンテナを中核とし、多様な機能を提供。
### Spring Boot
- Spring Frameworkをもっと簡単にBootstrapするもの。
    - XMLその他の設定ファイル書く量激減
    - 組み込みWebサーバー内蔵（デフォルトはTomcat；JettyやUndertowに切替可。どれも使ったことないけど。）
    - starterによる依存関係や構成の自動化
### Spring
- 以上のもの及びその背後の自然人・団体に使うUmbrella term。
- ただし、Spring Frameworkの略などとして使われることもあり（本ドキュメント含む）、コンテキストが大事。
## 重要な概念
### DI
- Dependency Injectionの略で、日本語では「依存性注入」と言う。
    - なぜ「依存性」と訳されたか個人的には不明。
- Injectionは、（Dependencyを）内部から生成するではなく、外部から渡す（注入する）行為を指す。
- Springでは、Bean登録したクラス等につき、自動的にインスタンスを作って注入してくれるので、DIを行っていると言える。
### IoCコンテナ
- DIを自動的に行うフレームワークを指す。
- Springの文脈では、Bean（インスタンス）の入れ物であり、その生成・管理・ライフサイクル制御・注入を行う。
## Spring Bootの便利な機能
### starter (spring-boot-starter-foo)
- その機能に必要なDependencyを全てまとめたパック。
### application.properties/yml
- パラメータを管理できる設定ファイル。
- 活用することで、変更時、Javaのコードを変更しなくて済む場合がある。
- .propertiesはkey=valueを記述したものであり、.ymlはYAML文法で記述できる。
    - (YAML嫌いだけど)後者は階層を可視化しているところにおいて優れている（と認めざるを得ない）。
### auto-configuration
- Dependencyライブラリや設定値から、必要なBeanや機能を自動で用意してくれる仕組み。
## よく使うアノテーション
### Beanの指定
#### @Component
- もっとも一般的なBeanの指定。
#### @Service
- 特に、ビジネスロジックを取り扱うことを明示した@Component。
#### @Repository
- データアクセス層（DAO）に使う特別な@Component。
#### @Controller
- Web層のController（伝統的なMVCのそれ）に使う特別な@Component。
- HTTPリクエストを処理し、レスポンス（View）を返すため、単独ではJSP等を返すような典型的なMVCに使う。
    - JSONやXMLで返したい場合は、@ResponseBodyの指定が必要。
##### @RestController
- 特別な@Controllerで、@Controllerと@ResponseBodyを内包したもののため、直接Restful APIに利用できる。
### 注入の指定
#### @Autowired
- Springに、必要なBeanを探し注入するよう指示するもの。
- Beanがない場合はエラーを吐く。
- Beanが複数ある場合は@Qualifierによる明示が必要な場合もある。
# RESTful API
## RESTの基本
### REST
- REpresentational State Transferの略。
- Webアーキテクチャの設計仕様の一つで、以下の原則を含むとされる。
    - Statelessness
    - リソースの一意的なURIの指定
    - インターフェイスの統一（HTTPメソッド・ステータスコードを正しく利用）
### RESTful API
- RESTの諸原則に則って構築された、HTTPでの呼び出しインターフェイス。
- REST APIとも。

### SOAP
- より厳密な（固い）プロトコルであり、HTTPに限定されず、より古いWebサービスで多用。
- 開発時において、RESTとは実質上二者択一で採用されることが多い。
## 重要な概念
### リソース
- 一つの（区切られた）情報（データまたは機能）のこと。
- RESTにおいて、ネット上で一意にアクセス可能であることが求められる。
### Stateless(ness)
- （サーバーが）各リクエスト間のユーザー状態の記憶に頼らない、即ち、各リクエストが自己完結していること。
### HTTPメソッド
- CRUDに**ルーズに**対応している。
    - POST: Create
    - GET: Read
    - PUT: Update
    - DELETE: Delete
### OpenAPI
- RESTful APIの仕様を機械可読な形式で記述するための規格。
    - 現在の規格は、YAMLまたはJSONで記述することを求めている。
#### Swagger
- OpenAPIの昔の名前。
- 現在は、OpenAPIを活用し、RESTful APIを設計するために使うツールセットの名前である。
## プラクティス
### RESTful APIとセッション管理
- Statelessnessはセッションを持たないことを求めるため、原則として、認証に必要な場合、トークンをリクエストごとに送る（HTTPヘッダーに付与する）必要がある。
- Cookieの利用は、自動送信のためStatelessnessを損ないやすいとされる。
- URI書換えは、RESTの原則から逸れたと考えられ、セキュリティ面でも問題が大きい。
### RESTful APIとレスポンスのフォーマット
- もっともよく使われるのはJSONであり、Spring Bootでのデフォルトでもある。
    - それ以外を使う場合は、追加のDependencyが必要になる。
- 希望形式は、Acceptヘッダーで明示的にネゴシエーションできる。
### RESTful APIとエラー返し
- ヘッダーでステータスコードとエラーメッセージを正しく返すことが求められる。
- エラーの内容・詳細・タイムスタンプ等をレスポンスボディで分かりやすく返すことが一般的である。
