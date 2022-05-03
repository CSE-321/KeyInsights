export function round(value, precision) {
  var multiplier = Math.pow(10, precision || 0);
  return Math.round(value * multiplier) / multiplier;
}

export function getProjectNameFromUrl(url) {
  return url.split('=').pop();
}
