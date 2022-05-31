export class Helper {
  static getDateFromTimestamp() {}

  static getLitteralTime(date: number) {
    return new Date(date).toLocaleTimeString('fr-FR', {
      hour: '2-digit',
      minute: '2-digit',
    });
  }

  static getLitteralDate(date: number) {
    return new Date(date).toLocaleDateString('fr-FR', {
      weekday: 'short',
      day: '2-digit',
      month: 'long',
    });
  }

  static getLitteralBirthDate(date: number) {
    return new Date(date).toLocaleDateString('fr-FR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
    });
  }

  static toDateInput(date: number) {
    return this.getLitteralBirthDate(date).split('/').reverse().join('-');
  }
}
