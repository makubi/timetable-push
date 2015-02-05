
package views.html.polymer

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),format.raw/*3.1*/("""<link rel="import" href=""""),_display_(/*3.27*/routes/*3.33*/.Assets.at("polymer/bower_components/polymer/polymer.html")),format.raw/*3.92*/("""">
<link rel="import" href=""""),_display_(/*4.27*/routes/*4.33*/.Assets.at("polymer/bower_components/paper-input/paper-input-decorator.html")),format.raw/*4.110*/("""">
<link rel="import" href=""""),_display_(/*5.27*/routes/*5.33*/.Assets.at("polymer/bower_components/paper-button/paper-button.html")),format.raw/*5.102*/("""">
<link rel="import" href=""""),_display_(/*6.27*/routes/*6.33*/.Assets.at("polymer/bower_components/core-ajax/core-ajax.html")),format.raw/*6.96*/("""">
<link rel="import" href=""""),_display_(/*7.27*/routes/*7.33*/.Assets.at("polymer/bower_components/paper-shadow/paper-shadow.html")),format.raw/*7.102*/("""">
<polymer-element name="school-login" constructor="SchoolLogin" attribute="school">
    <template>
        <style>
            :host """),format.raw/*11.19*/("""{"""),format.raw/*11.20*/("""
                """),format.raw/*12.17*/("""display: block;
                padding: 8px;
                position: relative;
                background-color: #E5E5E5;
                overflow: auto;
            """),format.raw/*17.13*/("""}"""),format.raw/*17.14*/("""

            """),format.raw/*19.13*/(""".card"""),format.raw/*19.18*/("""{"""),format.raw/*19.19*/("""
                """),format.raw/*20.17*/("""margin: 8px;
                font-size: 14px;
                padding-left: 16px;
                padding-right: 16px;
                padding-bottom: 8px;
                padding-top: 8px;
                background: #fff;
                line-height: 1.5;
            """),format.raw/*28.13*/("""}"""),format.raw/*28.14*/("""

            """),format.raw/*30.13*/(""".cardhead"""),format.raw/*30.22*/("""{"""),format.raw/*30.23*/("""
                """),format.raw/*31.17*/("""font-size: 24px;
                color: #FF9800;
                margin-bottom: 8px;
            """),format.raw/*34.13*/("""}"""),format.raw/*34.14*/("""

            """),format.raw/*36.13*/("""paper-ripple """),format.raw/*36.26*/("""{"""),format.raw/*36.27*/("""
                """),format.raw/*37.17*/("""color: #FF9800;
            """),format.raw/*38.13*/("""}"""),format.raw/*38.14*/("""

        """),format.raw/*40.9*/("""</style>


        <div layout horizontal around-justified>

            <paper-shadow class="card" z="1" >
                <div class="cardhead">
                    Login
                </div>
                <div layout center class="card">
                    <paper-input-decorator label="Username" flex floatingLabel="true" id="usernameDec">
                        <input id="username" is="paper-input">
                    </paper-input-decorator>
                    <paper-input-decorator label="Password" flex floatingLabel="true" id="passwordDec">
                        <input id="password" is="paper-input">
                    </paper-input-decorator>

                    <paper-button on-tap=""""),format.raw/*57.43*/("""{"""),format.raw/*57.44*/("""{"""),format.raw/*57.45*/("""loadLists"""),format.raw/*57.54*/("""}"""),format.raw/*57.55*/("""}"""),format.raw/*57.56*/("""" raised>Login</paper-button>
                </div>
            </paper-shadow>

            <paper-shadow class="card" z="1" >
                <div class="cardhead">
                    """),format.raw/*63.21*/("""{"""),format.raw/*63.22*/("""{"""),format.raw/*63.23*/("""school.displayName"""),format.raw/*63.41*/("""}"""),format.raw/*63.42*/("""}"""),format.raw/*63.43*/("""
                """),format.raw/*64.17*/("""</div>

                <div>
                    """),format.raw/*67.21*/("""{"""),format.raw/*67.22*/("""{"""),format.raw/*67.23*/("""school.address"""),format.raw/*67.37*/("""}"""),format.raw/*67.38*/("""}"""),format.raw/*67.39*/(""" """),format.raw/*67.40*/("""<br>
                    """),format.raw/*68.21*/("""{"""),format.raw/*68.22*/("""{"""),format.raw/*68.23*/("""school.server"""),format.raw/*68.36*/("""}"""),format.raw/*68.37*/("""}"""),format.raw/*68.38*/(""" """),format.raw/*68.39*/("""<br>
                    """),format.raw/*69.21*/("""{"""),format.raw/*69.22*/("""{"""),format.raw/*69.23*/("""school.loginName"""),format.raw/*69.39*/("""}"""),format.raw/*69.40*/("""}"""),format.raw/*69.41*/("""
                """),format.raw/*70.17*/("""</div>
            </paper-shadow>
        </div>

        <core-ajax
            id="ajax"
            url=""
            method="post"
            handleAs="json"
            on-core-response=""""),format.raw/*79.31*/("""{"""),format.raw/*79.32*/("""{"""),format.raw/*79.33*/("""handleResponse"""),format.raw/*79.47*/("""}"""),format.raw/*79.48*/("""}"""),format.raw/*79.49*/(""""></core-ajax>

    </template>

    <script>
        Polymer("school-login", """),format.raw/*84.33*/("""{"""),format.raw/*84.34*/("""

            """),format.raw/*86.13*/("""school: null,

            stringify: function(obj)"""),format.raw/*88.37*/("""{"""),format.raw/*88.38*/("""
                """),format.raw/*89.17*/("""return JSON.stringify(obj);
            """),format.raw/*90.13*/("""}"""),format.raw/*90.14*/(""",


            loadLists: function()"""),format.raw/*93.34*/("""{"""),format.raw/*93.35*/("""
                """),format.raw/*94.17*/("""var user = this.$.username.value;
                var pass = this.$.password.value;

                var url = "/loadLists?server=" + encodeURIComponent(this.school.server) +
                    "&school=" + encodeURIComponent(this.school.loginName) +
                    "&user=" + encodeURIComponent(user) +
                    "&password=" + encodeURIComponent(pass);
                this.$.ajax.url = url;
                this.$.ajax.go();

            """),format.raw/*104.13*/("""}"""),format.raw/*104.14*/(""",

            handleResponse: function(event, data)"""),format.raw/*106.50*/("""{"""),format.raw/*106.51*/("""
                """),format.raw/*107.17*/("""this.fire("listsLoaded", data.response[0]);
            """),format.raw/*108.13*/("""}"""),format.raw/*108.14*/("""

        """),format.raw/*110.9*/("""}"""),format.raw/*110.10*/(""");
    </script>

</polymer-element>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Dec 21 16:49:09 CET 2014
                  SOURCE: /home/basti/IdeaProjects/UntisCrawler/app/views/polymer/login.scala.html
                  HASH: a3ecca645b4dd21ba1e1727d9a06f060cdf442da
                  MATRIX: 506->1|595->3|623->5|675->31|689->37|768->96|823->125|837->131|935->208|990->237|1004->243|1094->312|1149->341|1163->347|1246->410|1301->439|1315->445|1405->514|1568->649|1597->650|1642->667|1839->836|1868->837|1910->851|1943->856|1972->857|2017->874|2315->1144|2344->1145|2386->1159|2423->1168|2452->1169|2497->1186|2622->1283|2651->1284|2693->1298|2734->1311|2763->1312|2808->1329|2864->1357|2893->1358|2930->1368|3670->2080|3699->2081|3728->2082|3765->2091|3794->2092|3823->2093|4039->2281|4068->2282|4097->2283|4143->2301|4172->2302|4201->2303|4246->2320|4324->2370|4353->2371|4382->2372|4424->2386|4453->2387|4482->2388|4511->2389|4564->2414|4593->2415|4622->2416|4663->2429|4692->2430|4721->2431|4750->2432|4803->2457|4832->2458|4861->2459|4905->2475|4934->2476|4963->2477|5008->2494|5231->2689|5260->2690|5289->2691|5331->2705|5360->2706|5389->2707|5495->2785|5524->2786|5566->2800|5645->2851|5674->2852|5719->2869|5787->2909|5816->2910|5881->2947|5910->2948|5955->2965|6441->3422|6471->3423|6552->3475|6582->3476|6628->3493|6713->3549|6743->3550|6781->3560|6811->3561
                  LINES: 19->1|22->1|24->3|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|27->6|27->6|28->7|28->7|28->7|32->11|32->11|33->12|38->17|38->17|40->19|40->19|40->19|41->20|49->28|49->28|51->30|51->30|51->30|52->31|55->34|55->34|57->36|57->36|57->36|58->37|59->38|59->38|61->40|78->57|78->57|78->57|78->57|78->57|78->57|84->63|84->63|84->63|84->63|84->63|84->63|85->64|88->67|88->67|88->67|88->67|88->67|88->67|88->67|89->68|89->68|89->68|89->68|89->68|89->68|89->68|90->69|90->69|90->69|90->69|90->69|90->69|91->70|100->79|100->79|100->79|100->79|100->79|100->79|105->84|105->84|107->86|109->88|109->88|110->89|111->90|111->90|114->93|114->93|115->94|125->104|125->104|127->106|127->106|128->107|129->108|129->108|131->110|131->110
                  -- GENERATED --
              */
          