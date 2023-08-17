package blog.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.ex.service.BlogService;

/**
 * @RequestMappingアノテーションは、HTTPリクエストに対するマッピングを設定するために使用されます。
 * "/user"と指定されているので、このコントローラーのすべてのエンドポイントは"/user/blog"で始まります。
 *エンドポイント（Endpoint）は、Webサービスにおいて、外部からアクセス可能なURLやURIのことを指します。
 *つまり、クライアント（Webブラウザやスマートフォンアプリなど）がWebサービスにリクエストを送信するために使用するURLのことです。
 */
@RequestMapping("/user/blog")
/**
 * @Controllerアノテーションは、Spring Frameworkがコンポーネントスキャン機能
 * によってこのクラスをDIコンテナに登録するために使用されます。
 * これにより、UserRegisterControllerクラスは、他のコンポーネントに注入されることができ,
 * システム内で一元的に管理されることができます。
 * コンポーネントスキャン機能は、Spring Frameworkにおいて、アノテーションで構成されたクラスを自動的に検出し、
 * DIコンテナに登録するための機能です
 * DI（Dependency Injection）コンテナは、Spring Frameworkが提供する機能の一つで、アプリケーションに必要なオブジェクトを管理する仕組みです。
 *通常、アプリケーションに必要なオブジェクトは、new演算子を使用してインスタンス化していますが、
 *DIコンテナを使用すると、オブジェクトの生成や注入を自動的に行うことができます。
 **/
@Controller
public class BlogContgroller {
	/**
	 * @Autowiredアノテーションは、DIコンテナが自動的にBlogServiceインスタンスを
	 * 注入するために使用されます。これにより、BlogControllerクラスは、
	 * BlogServiceクラスのメソッドを呼び出すことができ、指定した処理を実行することができます。
	 **/
	@Autowired 
	private BlogService blogService;
	/**
	 * HttpSessionとは、Webアプリケーションにおいて、ユーザーごとに状態を保持するためのオブジェクトです。
	 * HTTPプロトコル()は、通信ごとに新しい接続を確立するため、ユーザーの状態を保持するためには、ユーザーが識別できる情報を保持しておく必要があります。
	 * HttpSessionは、Webアプリケーション内でユーザーごとに生成され、サーバー側で管理されます。ユーザーごとに一意なIDが割り当てられ、
	 * このIDを使用して、サーバー上でユーザーのセッション情報を特定します。
	 * HttpSessionは、主に以下のような目的で使用されます。
	 * ログイン状態の維持
	 * ショッピングカートやフォームの入力情報の保存
	 * ユーザーごとに異なる情報の表示
	 * このように、HttpSessionを使用することで、ユーザーごとに状態を保持し、よりパーソナライズされたWebアプリケーションを実現することができます。
	 * しかし、HttpSessionにはセッションの有効期限やサイズ制限などの制限があるため、適切な管理が必要です。
	 * また、セッション情報には機密性があるため、適切なセキュリティ対策が必要となります。**/
	/**
	 * プロトコル（protocol）は、通信やデータのやり取りを行う際の手順や規約のことを指します
	 * 。つまり、ある種の共通のやり取りの方式やルールを決め、それに従って通信やデータのやり取りを行うことで、
	 * 情報の正確な伝達や効率的な処理を実現するための仕組みです。**/
}
