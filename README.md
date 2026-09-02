# Testing

## Atlassian PR merge notification

The workflow `.github/workflows/notify-atlassian-on-pr-merge.yml` sends a webhook
notification to an Atlassian Automation flow whenever a pull request is merged.

To enable it, add repository secrets named `ATLASSIAN_WEBHOOK_URL` and
`ATLASSIAN_WEBHOOK_SECRET` containing the Atlassian Automation Incoming Webhook
URL and token, under
**Settings → Secrets and variables → Actions**.
