/** @jsxImportSource @emotion/react */
import { ErrorBoundary } from './errorBoundary';
import { FC, ReactChild } from 'react';

// We cannot use lokalise here since this is the top level error boundary
// and the lokalise logic (among other things) might have failed
const errorMessages = {
  en: 'An error occurred. Try refreshing the window, and contact support if the problem persists.',
  };

const getDisplayedErrorMessages = () => {
  const messages = [];
  messages.unshift(errorMessages.en);
  return messages;
};

const Fallback: FC = () => {
  let messages;
  // Since this is in the top level error boundary, we want to be 100% safe
  // so therefore we put this in a try catch.
  try {
    messages = getDisplayedErrorMessages();
  } catch (error) {
    messages = [errorMessages.en];
  }
  return (
    <div style={{ margin: '16px' }}>
      {messages.map((message) => (
        <span>
          {message}
        </span>
      ))}
    </div>
  );
};

export const TopLevelErrorBoundary: FC<{
  children: ReactChild;
}> = ({ children }) => (
  <ErrorBoundary fallback={<Fallback />}>{children}</ErrorBoundary>
);
