export class Project {
  constructor(id, name, type, lead, image, created, issues) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.lead = lead;
    this.image = image;
    this.created = created;
    this.issues = issues;
  }

  getId() {
    return this.id;
  }

  setId(id) {
    this.id = id;
  }

  getName() {
    return this.name;
  }

  setName(name) {
    this.name = name;
  }

  getType() {
    return this.type;
  }

  setType(type) {
    this.type = type;
  }

  getLead() {
    return this.lead;
  }

  setLead(lead) {
    this.lead = lead;
  }

  getImage() {
    return this.image;
  }

  setImage(image) {
    this.image = image;
  }

  getCreated() {
    return this.created;
  }

  setCreated(created) {
    this.created = created;
  }

  getIssues() {
    return this.issues;
  }

  setIssues(issues) {
    this.issues = issues;
  }
}
