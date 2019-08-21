package hr.foi.diplomski.rad.core.model

import groovy.transform.ToString
import hr.foi.diplomski.rad.enums.Codebooks

@ToString
class CodebookEntry {
  Integer id
  String name
  Codebooks type
}
