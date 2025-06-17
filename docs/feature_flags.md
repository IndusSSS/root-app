# Feature Flags

This project supports optional features controlled via `edge_features.yaml` or
via a Gradle property `-PedgeFeatures`.

## Enabling Features
Edit `edge_features.yaml` and set the desired flags to `true`:

```yaml
features:
  ai_advanced: true
  kiosk_mode: true
```

Alternatively pass a comma separated list when building:

```bash
./gradlew assembleEdgeDebug -PedgeFeatures=ai_advanced,kiosk_mode
```

## CI Matrix
The CI workflow builds two variants: default with all flags disabled and
`fullOptionsDebug` with all flags enabled.

## Permissions and Size Impact
Each feature may introduce extra permissions or native libraries. Consult the
AndroidManifest and build reports when enabling a feature.

