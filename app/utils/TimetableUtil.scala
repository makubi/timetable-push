package utils

import model.TimetableData
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormat}
import play.api.Logger

object TimetableUtil {

  def getIrregularEvents(timetableData: TimetableData) ={
    val data = timetableData.elementIds.map(key => timetableData.elementPeriods.get(String.valueOf(key)).get).flatten
    val specialLessons = data.filter(d => !d.cellState.equals("STANDARD"))
    specialLessons
  }

  def getRequestDate(): List[Int] = {
   localDateToTimetableDate(LocalDate.now()) :: localDateToTimetableDate(LocalDate.now().plusWeeks(1)) :: List()
  }

  def localDateToTimetableDate(date: LocalDate): Int = {
    date.getYear() * 10000 + date.getMonthOfYear * 100 + date.getDayOfMonth
  }

  def timeTableDateToLocalDate(date: Int): LocalDate = {
    LocalDate.parse(date.toString(), DateTimeFormat.forPattern("yyyyMMdd"))
  }

}
