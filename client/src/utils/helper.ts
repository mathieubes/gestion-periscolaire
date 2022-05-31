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
}
