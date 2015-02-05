
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
object schoolsearch extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),format.raw/*3.1*/("""<link rel="import" href=""""),_display_(/*3.27*/routes/*3.33*/.Assets.at("polymer/bower_components/polymer/polymer.html")),format.raw/*3.92*/("""">
<link rel="import" href=""""),_display_(/*4.27*/routes/*4.33*/.Assets.at("polymer/bower_components/paper-input/paper-input-decorator.html")),format.raw/*4.110*/("""">
<link rel="import" href=""""),_display_(/*5.27*/routes/*5.33*/.Assets.at("polymer/bower_components/paper-icon-button/paper-icon-button.html")),format.raw/*5.112*/("""">
<link rel="import" href=""""),_display_(/*6.27*/routes/*6.33*/.Assets.at("polymer/bower_components/core-ajax/core-ajax.html")),format.raw/*6.96*/("""">
<link rel="import" href=""""),_display_(/*7.27*/routes/*7.33*/.Assets.at("polymer/bower_components/paper-shadow/paper-shadow.html")),format.raw/*7.102*/("""">
<link rel="import" href=""""),_display_(/*8.27*/routes/*8.33*/.Assets.at("polymer/bower_components/paper-ripple/paper-ripple.html")),format.raw/*8.102*/("""">

<polymer-element name="school-search" constructor="SchoolSearch">
    <template>
        <style>
            :host """),format.raw/*13.19*/("""{"""),format.raw/*13.20*/("""
                """),format.raw/*14.17*/("""display: block;
                padding: 8px;
                position: relative;
                background-color: #E5E5E5;
                overflow: auto;
            """),format.raw/*19.13*/("""}"""),format.raw/*19.14*/("""

            """),format.raw/*21.13*/(""".card"""),format.raw/*21.18*/("""{"""),format.raw/*21.19*/("""
                """),format.raw/*22.17*/("""margin: 8px;
                font-size: 14px;
                padding-left: 16px;
                padding-right: 16px;
                padding-bottom: 8px;
                padding-top: 8px;
                background: #fff;
                line-height: 1.5;
            """),format.raw/*30.13*/("""}"""),format.raw/*30.14*/("""

            """),format.raw/*32.13*/(""".cardhead"""),format.raw/*32.22*/("""{"""),format.raw/*32.23*/("""
                """),format.raw/*33.17*/("""font-size: 24px;
                color: #FF9800;
                margin-bottom: 8px;
            """),format.raw/*36.13*/("""}"""),format.raw/*36.14*/("""

            """),format.raw/*38.13*/("""paper-ripple """),format.raw/*38.26*/("""{"""),format.raw/*38.27*/("""
                """),format.raw/*39.17*/("""color: #FF9800;
            """),format.raw/*40.13*/("""}"""),format.raw/*40.14*/("""


        """),format.raw/*43.9*/("""</style>

        <div layout horizontal center>
            <paper-input-decorator label="School Search" flex floatingLabel="true" id="searchParamsDec">
                <input id="searchParams" is="paper-input" on-keypress=""""),format.raw/*47.72*/("""{"""),format.raw/*47.73*/("""{"""),format.raw/*47.74*/("""keypressHandler"""),format.raw/*47.89*/("""}"""),format.raw/*47.90*/("""}"""),format.raw/*47.91*/("""">
            </paper-input-decorator>

            <paper-icon-button icon="search" on-tap=""""),format.raw/*50.54*/("""{"""),format.raw/*50.55*/("""{"""),format.raw/*50.56*/("""search"""),format.raw/*50.62*/("""}"""),format.raw/*50.63*/("""}"""),format.raw/*50.64*/("""" />
        </div>

        <div horizontal layout wrap around-justified id="resultbox">
            <template repeat=""""),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""{"""),format.raw/*54.33*/("""school in schools"""),format.raw/*54.50*/("""}"""),format.raw/*54.51*/("""}"""),format.raw/*54.52*/("""">

                    <paper-shadow class="card" on-tap=""""),format.raw/*56.56*/("""{"""),format.raw/*56.57*/("""{"""),format.raw/*56.58*/("""onSchoolSelected"""),format.raw/*56.74*/("""}"""),format.raw/*56.75*/("""}"""),format.raw/*56.76*/("""" data-school=""""),format.raw/*56.91*/("""{"""),format.raw/*56.92*/("""{"""),format.raw/*56.93*/("""stringify(school)"""),format.raw/*56.110*/("""}"""),format.raw/*56.111*/("""}"""),format.raw/*56.112*/("""" z="1">
                        <div class="cardhead">
                            """),format.raw/*58.29*/("""{"""),format.raw/*58.30*/("""{"""),format.raw/*58.31*/("""school.displayName"""),format.raw/*58.49*/("""}"""),format.raw/*58.50*/("""}"""),format.raw/*58.51*/("""
                        """),format.raw/*59.25*/("""</div>

                        <div>
                            """),format.raw/*62.29*/("""{"""),format.raw/*62.30*/("""{"""),format.raw/*62.31*/("""school.address"""),format.raw/*62.45*/("""}"""),format.raw/*62.46*/("""}"""),format.raw/*62.47*/(""" """),format.raw/*62.48*/("""<br>
                            """),format.raw/*63.29*/("""{"""),format.raw/*63.30*/("""{"""),format.raw/*63.31*/("""school.server"""),format.raw/*63.44*/("""}"""),format.raw/*63.45*/("""}"""),format.raw/*63.46*/(""" """),format.raw/*63.47*/("""<br>
                            """),format.raw/*64.29*/("""{"""),format.raw/*64.30*/("""{"""),format.raw/*64.31*/("""school.loginName"""),format.raw/*64.47*/("""}"""),format.raw/*64.48*/("""}"""),format.raw/*64.49*/("""
                        """),format.raw/*65.25*/("""</div>
                        <paper-ripple fit></paper-ripple>
                    </paper-shadow>


            </template>
        </div>

        <core-ajax
            id="ajax"
            url="/search?searchParam="
            method="post"
            handleAs="json"
            on-core-response=""""),format.raw/*78.31*/("""{"""),format.raw/*78.32*/("""{"""),format.raw/*78.33*/("""handleResponse"""),format.raw/*78.47*/("""}"""),format.raw/*78.48*/("""}"""),format.raw/*78.49*/(""""></core-ajax>

    </template>

    <script>
        Polymer("school-search", """),format.raw/*83.34*/("""{"""),format.raw/*83.35*/("""

            """),format.raw/*85.13*/("""schools: [],

            search: function() """),format.raw/*87.32*/("""{"""),format.raw/*87.33*/("""
                """),format.raw/*88.17*/("""var query = this.$.searchParams.value
                this.$.ajax.url = "/search?searchParam=" + encodeURIComponent(query);
                this.$.ajax.go();
            """),format.raw/*91.13*/("""}"""),format.raw/*91.14*/(""",

            keypressHandler: function(event, detail, handler)"""),format.raw/*93.62*/("""{"""),format.raw/*93.63*/("""
                """),format.raw/*94.17*/("""if(event.keyCode === 13)"""),format.raw/*94.41*/("""{"""),format.raw/*94.42*/("""
                    """),format.raw/*95.21*/("""this.search();
                """),format.raw/*96.17*/("""}"""),format.raw/*96.18*/("""
            """),format.raw/*97.13*/("""}"""),format.raw/*97.14*/(""",

            handleResponse: function(event, data)"""),format.raw/*99.50*/("""{"""),format.raw/*99.51*/("""
                """),format.raw/*100.17*/("""console.log(data);
                if(data.response.result)"""),format.raw/*101.41*/("""{"""),format.raw/*101.42*/("""
                    """),format.raw/*102.21*/("""this.schools = data.response.result.schools;
                    this.$.searchParamsDec.label = this.schools.length + " matches";
                """),format.raw/*104.17*/("""}"""),format.raw/*104.18*/("""else"""),format.raw/*104.22*/("""{"""),format.raw/*104.23*/("""
                    """),format.raw/*105.21*/("""this.schools = [];
                    this.$.searchParamsDec.label =  data.response.error.message;
                """),format.raw/*107.17*/("""}"""),format.raw/*107.18*/("""
            """),format.raw/*108.13*/("""}"""),format.raw/*108.14*/(""",

            onSchoolSelected: function(e,f,g)"""),format.raw/*110.46*/("""{"""),format.raw/*110.47*/("""
                """),format.raw/*111.17*/("""var school = g.attributes["data-school" ].value;
                this.fire("schoolSelected", school);
            """),format.raw/*113.13*/("""}"""),format.raw/*113.14*/(""",

            stringify: function(obj)"""),format.raw/*115.37*/("""{"""),format.raw/*115.38*/("""
                """),format.raw/*116.17*/("""return JSON.stringify(obj);
            """),format.raw/*117.13*/("""}"""),format.raw/*117.14*/("""

        """),format.raw/*119.9*/("""}"""),format.raw/*119.10*/(""");
    </script>

</polymer-element>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Dec 21 14:23:18 CET 2014
                  SOURCE: /home/basti/IdeaProjects/UntisCrawler/app/views/polymer/schoolsearch.scala.html
                  HASH: a0f1655929bc2aed36b5adc30d9aa0e30f4b849e
                  MATRIX: 513->1|602->3|630->5|682->31|696->37|775->96|830->125|844->131|942->208|997->237|1011->243|1111->322|1166->351|1180->357|1263->420|1318->449|1332->455|1422->524|1477->553|1491->559|1581->628|1728->747|1757->748|1802->765|1999->934|2028->935|2070->949|2103->954|2132->955|2177->972|2475->1242|2504->1243|2546->1257|2583->1266|2612->1267|2657->1284|2782->1381|2811->1382|2853->1396|2894->1409|2923->1410|2968->1427|3024->1455|3053->1456|3091->1467|3344->1692|3373->1693|3402->1694|3445->1709|3474->1710|3503->1711|3625->1805|3654->1806|3683->1807|3717->1813|3746->1814|3775->1815|3923->1935|3952->1936|3981->1937|4026->1954|4055->1955|4084->1956|4171->2015|4200->2016|4229->2017|4273->2033|4302->2034|4331->2035|4374->2050|4403->2051|4432->2052|4478->2069|4508->2070|4538->2071|4650->2155|4679->2156|4708->2157|4754->2175|4783->2176|4812->2177|4865->2202|4959->2268|4988->2269|5017->2270|5059->2284|5088->2285|5117->2286|5146->2287|5207->2320|5236->2321|5265->2322|5306->2335|5335->2336|5364->2337|5393->2338|5454->2371|5483->2372|5512->2373|5556->2389|5585->2390|5614->2391|5667->2416|6002->2723|6031->2724|6060->2725|6102->2739|6131->2740|6160->2741|6267->2820|6296->2821|6338->2835|6411->2880|6440->2881|6485->2898|6683->3068|6712->3069|6804->3133|6833->3134|6878->3151|6930->3175|6959->3176|7008->3197|7067->3228|7096->3229|7137->3242|7166->3243|7246->3295|7275->3296|7321->3313|7409->3372|7439->3373|7489->3394|7664->3540|7694->3541|7727->3545|7757->3546|7807->3567|7952->3683|7982->3684|8024->3697|8054->3698|8131->3746|8161->3747|8207->3764|8350->3878|8380->3879|8448->3918|8478->3919|8524->3936|8593->3976|8623->3977|8661->3987|8691->3988
                  LINES: 19->1|22->1|24->3|24->3|24->3|24->3|25->4|25->4|25->4|26->5|26->5|26->5|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|34->13|34->13|35->14|40->19|40->19|42->21|42->21|42->21|43->22|51->30|51->30|53->32|53->32|53->32|54->33|57->36|57->36|59->38|59->38|59->38|60->39|61->40|61->40|64->43|68->47|68->47|68->47|68->47|68->47|68->47|71->50|71->50|71->50|71->50|71->50|71->50|75->54|75->54|75->54|75->54|75->54|75->54|77->56|77->56|77->56|77->56|77->56|77->56|77->56|77->56|77->56|77->56|77->56|77->56|79->58|79->58|79->58|79->58|79->58|79->58|80->59|83->62|83->62|83->62|83->62|83->62|83->62|83->62|84->63|84->63|84->63|84->63|84->63|84->63|84->63|85->64|85->64|85->64|85->64|85->64|85->64|86->65|99->78|99->78|99->78|99->78|99->78|99->78|104->83|104->83|106->85|108->87|108->87|109->88|112->91|112->91|114->93|114->93|115->94|115->94|115->94|116->95|117->96|117->96|118->97|118->97|120->99|120->99|121->100|122->101|122->101|123->102|125->104|125->104|125->104|125->104|126->105|128->107|128->107|129->108|129->108|131->110|131->110|132->111|134->113|134->113|136->115|136->115|137->116|138->117|138->117|140->119|140->119
                  -- GENERATED --
              */
          